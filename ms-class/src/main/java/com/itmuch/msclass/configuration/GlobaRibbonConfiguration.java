package com.itmuch.msclass.configuration;

import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import ribbonconfiguration.RibbonConfiguration;

/**
 * @program: ms-class
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-10-20 18:06
 **/
@Configuration
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class GlobaRibbonConfiguration {

}

