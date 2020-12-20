/*
 * xxx股份有限公司版权所有
 */
package com.darben;

import com.alibaba.fastjson.JSON;
import com.darben.model.OrderInfo;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 类功能说明
 *
 * @author darben
 * @version 1.0.0
 * @date 2020/11/29
 */
@Slf4j
public class RibbonTests extends TestBaseService {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    private SpringClientFactory springClientFactory;

    private String providerServerName = "exec-sub-orderserver";

    /**
     * 方法说明
     * <p>
     *
     * @author darben
     * @date 2020/11/29 20:46
     * @version 1.0.0
     */
    @Test
    public void testRibbon() {
        String applicationName = "exec-sub-orderserver";
        OrderInfo response = restTemplate.getForObject("http://" + applicationName + "/sv/order/queryOrderInfoById", OrderInfo.class);
        log.info("response:{}", JSON.toJSONString(response));
    }

    /**
     * IBalance 单元测试
     * <p>
     *
     * @author darben
     * @date 2020/11/29 21:18
     * @version 1.0.0
     */
    @Test
    public void testILoanBalance() {
        ILoadBalancer loadBalancer = springClientFactory.getLoadBalancer(providerServerName);
        Server server = loadBalancer.chooseServer(null);
        String serverId = server.getId();
        log.info("serverId:{}", serverId);
    }
}
