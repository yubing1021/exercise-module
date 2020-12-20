/*
 * xxx股份有限公司版权所有
 */
package com.darben.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 类功能说明
 *
 * @author darben
 * @version 1.0.0
 * @date 2020/11/29
 */
@Configuration
public class RibbonConfig {

    /**
     * RestTemplate
     * <p>
     *
     * @author darben
     * @date 2020/11/30 22:24
     * @version 1.0.0
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * IRule
     * <p>
     *
     * @author darben
     * @date 2020/11/30 22:24
     * @version 1.0.0
     */
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
