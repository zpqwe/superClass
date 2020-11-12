package ribbonconfiguration;

import com.itmuch.msclass.ribbon.MyRibbonRule;
import com.itmuch.msclass.ribbon.MyRibbonRuleV2;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: ms-class
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-10-20 17:40
 **/
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule(){
        return new MyRibbonRuleV2();
    }
}

