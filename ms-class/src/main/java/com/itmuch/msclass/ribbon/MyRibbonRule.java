package com.itmuch.msclass.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.discovery.ConsulServer;
import org.springframework.cloud.consul.discovery.ConsulServerUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @program: ms-class
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-10-20 18:17
 **/
public class MyRibbonRule extends AbstractLoadBalancerRule {

    private static final String JIFANG = "JIFANG";
    @Autowired
    private ConsulDiscoveryProperties consulDiscoveryProperties;
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        // 读取配置
    }

    @Override
    public Server choose(Object o) {
        // 获得想要调用微服务实例列表
        ILoadBalancer loadBalancer = this.getLoadBalancer();
//        getReachableServers 获得微服务健康检查ok，可用的实例
        List<Server> servers = loadBalancer.getReachableServers();
        // 筛选出机房相同的实例列表
        //课程微服务所配置的tag
        List<String> tags = consulDiscoveryProperties.getTags();
        //ConsulServerUtils 提供的类，将集合转为map
        // 课程微服务所配置的元数据
        Map<String, String> metadata = ConsulServerUtils.getMetadata(tags);
        List<Server> JiFangMatchService = servers.stream()
                .filter(server -> {
                    ConsulServer consulServer = (ConsulServer) server;
                    Map<String, String> tagsMetadata = consulServer.getMetadata();
                    return Objects.equals(metadata.get(JIFANG), tagsMetadata.get(JIFANG));
                }).collect(Collectors.toList());
        // 随机返回一个实例
        if(CollectionUtils.isEmpty(JiFangMatchService)){
            return this.randomChose(servers);
        }
        return this.randomChose(JiFangMatchService);
    }
    private Server randomChose(List<Server> servers){
        int i = ThreadLocalRandom.current().nextInt(servers.size());
        return servers.get(i);
    }
}

