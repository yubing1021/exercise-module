package com.darben.service.remote;

import com.darben.model.OrderInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
*@Description:订单远程调用Sercie
*@Param:
*@return:
*@date: 2020/4/5
*/
@FeignClient(name = "exec-sub-orderserver",fallback = HystrixOrderRemoteFallBack.class )
public interface IOrderRemoteService {

    @RequestMapping(value = "/sv/order/queryOrderInfoById")
    public OrderInfo queryOrderInfoById(@RequestParam("id") String id);

}
