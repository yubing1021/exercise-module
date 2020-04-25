package com.darben.controller;

import com.darben.model.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @description: 业务层订单前端控制器
 * @author: darben
 * @create: 2020-04-05 10:40
 */
@RestController
@RequestMapping(value = "/sv/order")
@Slf4j
public class OrderInfoSvController {

    @RequestMapping(value = "/queryOrderInfoById")
    public OrderInfo queryOrderInfoById(String id){
        log.info("queryOrderInfoById id={}",id);

        //构建ordeinfo信息
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(id);
        orderInfo.setUserid("100121212");
        orderInfo.setGoodsName("婴儿尿不湿");
        orderInfo.setPrice("299.90");
        orderInfo.setPayType("在线支付");
        orderInfo.setReceiveAddress("湖北省黄冈市蕲春县 余先生 186****9700");
        orderInfo.setCreateTime(new Date());
        return orderInfo;
    }

}
