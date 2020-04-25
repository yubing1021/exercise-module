package com.darben.service;

import com.darben.model.OrderInfo;
import com.darben.service.remote.IOrderRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-05 10:50
 */
@Slf4j
@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private IOrderRemoteService orderRemoteService;

    @Override
    public OrderInfo queryOrderInfoById(String id) {
        //远程调用查询
        OrderInfo orderInfo = orderRemoteService.queryOrderInfoById(id);
        log.info("orderinfo:{}",orderInfo);

        return orderInfo;
    }

}
