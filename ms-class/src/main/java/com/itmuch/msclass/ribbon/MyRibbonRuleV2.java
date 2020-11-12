package com.itmuch.msclass.ribbon;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.health.model.HealthService;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.discovery.ConsulServer;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @program: ms-class
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-10-20 19:01
 **/
public class MyRibbonRuleV2 extends AbstractLoadBalancerRule {
    @Autowired
    private ConsulDiscoveryProperties consulDiscoveryProperties;
    @Autowired
    private ConsulClient consulClient;
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {

        // 1、获得想要调用微服务实例列表
        ILoadBalancer lb = this.getLoadBalancer();

        ZoneAwareLoadBalancer loadBalancer = (ZoneAwareLoadBalancer) lb;
        // 想要调用的微服务名称(ms-user)
        String name = loadBalancer.getName();
        // 本地配置的tag
        List<String> tags = consulDiscoveryProperties.getTags();
        // 筛选出JIFANG等于NJ的数据
        String jiFangTag = tags.stream()
                .filter(tag -> tag.startsWith("JIFANG"))
                .findFirst()
                .orElse(null);
        //2、 筛选出机房相同的实例列表
        Response<List<HealthService>> serviceRespose = this.consulClient.getHealthServices(name, jiFangTag, true, QueryParams.DEFAULT);
        //当前健康的微服务实例
        List<HealthService> healthServices = serviceRespose.getValue();
        if(CollectionUtils.isEmpty(healthServices)){
            Response<List<HealthService>> allHealthServiceResponses = this.consulClient.getHealthServices(name, null, true, QueryParams.DEFAULT);
            healthServices=allHealthServiceResponses.getValue();
        }
        List<ConsulServer> consulServers =
                healthServices.stream()
                .map(healthService -> new ConsulServer(healthService))
                .collect(Collectors.toList());
        if(CollectionUtils.isEmpty(consulServers)){
            return null;
        }
        //3、 随机返回一个实例
        return this.randomChose(consulServers);
    }
    private Server randomChose(List<ConsulServer> servers){
        int i = ThreadLocalRandom.current().nextInt(servers.size());
        return servers.get(i);
    }
}

