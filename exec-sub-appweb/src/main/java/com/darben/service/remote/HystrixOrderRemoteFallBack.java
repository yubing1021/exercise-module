package com.darben.service.remote;

import com.darben.model.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-05 10:53
 */
@Slf4j
@Component
public class HystrixOrderRemoteFallBack implements IOrderRemoteService{

    @Override
    public OrderInfo queryOrderInfoById(String id) {
        log.info("fallback -> queryOrderInfoById :{} , 404", id);
        return null;
    }
}
