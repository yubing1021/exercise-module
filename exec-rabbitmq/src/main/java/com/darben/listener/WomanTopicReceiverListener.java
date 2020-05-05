package com.darben.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description:
 * @author: darben
 * @create: 2020-05-05 16:41
 */
@Component
@RabbitListener(queues = "topic.woman")
public class WomanTopicReceiverListener {

    @RabbitHandler
    public void process(Map<String,Object> message){
        System.out.println("WomanTopicReceiverListener消费者收到消息:\t"+message.toString());
    }

}
