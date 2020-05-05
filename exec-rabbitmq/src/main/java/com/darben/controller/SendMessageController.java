package com.darben.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description: 发送消息的控制器
 * @author: darben
 * @create: 2020-05-05 15:05
 */
@RestController
@RequestMapping("/send")
public class SendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/sendDirectMessage")
    public String sendDirectMessage(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello !";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        // 将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("productDirectExchange", "productDirectRouting", map);
        return createTime+"\t success";
    }

    @RequestMapping(value = "/sendTopicMessage")
    public String sendTopicMessage(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, man !";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        // 将消息携带绑定键值：topic.man 发送到交换机topicExchange，无需关注Queue
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", map);
        return createTime+"\t success";
    }

    @RequestMapping(value = "/sendTopicMessage2")
    public String sendTopicMessage2(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, woman !";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        // 将消息携带绑定键值：topic.man 发送到交换机topicExchange，无需关注Queue
        rabbitTemplate.convertAndSend("topicExchange", "topic.woman", map);
        return createTime+"\t success";
    }

    @RequestMapping(value = "/sendFanoutMessage")
    public String sendFanoutMessage(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, fanout !";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        // 将消息携带绑定键值：topic.man 发送到交换机topicExchange，无需关注Queue
        rabbitTemplate.convertAndSend("fanoutExchange", null, map.toString());
        return createTime+"\t success";
    }
}
