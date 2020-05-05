package com.darben.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description: 消息接收监听
 * @author: darben
 * @create: 2020-05-05 15:18
 */
@Component
@RabbitListener(queues = "productDirectQueue")
public class DirectReceiverListener {

    @RabbitHandler
    public void process(Map message){
        System.out.println("DirectReceiver消费者收到消息:\t"+message.toString());
    }
}
