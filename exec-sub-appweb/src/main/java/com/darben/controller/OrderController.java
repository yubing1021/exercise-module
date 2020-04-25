package com.darben.controller;

import com.darben.model.OrderInfo;
import com.darben.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @description: 订单前端控制器
 * @author: darben
 * @create: 2020-04-05 00:53
 */
@Slf4j
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
    *@Description: 根据id查询
    *@Param:
    *@return:
    *@date: 2020/4/5
    */
    @RequestMapping(value = "/queryOrderInfoById")
    public OrderInfo queryOrderInfoById(String id){
        log.info("queryOrderInfoById id:{}",id);
        return orderService.queryOrderInfoById(id);
    }

}
