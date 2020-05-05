package com.darben.config;

import com.darben.listener.MyAckReceiverListener;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * 消费者接收消息的消息确认机制：
 * 1、自动确认，默认 AcknowledgeMode.NONE
 * 2、根据情况确认-不做说明
 * 3、手动确认，这个比较关键，也是我们配置接收消息确认机制时，多数选择的模式
 *    basic.ack用于肯定确认
 *    basic.nack用于否定确认（注意：这是AMQP 0-9-1的RabbitMQ扩展）
 *    basic.reject用于否定确认，但与basic.nack相比有一个限制:一次只能拒绝单条消息
 *
 * @author: darben
 * @create: 2020-05-05 21:30
 */
@Configuration
public class MessageListenerConfig {

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    @Autowired
    private MyAckReceiverListener myAckReceiverListener;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cachingConnectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        // RabbitMQ默认是自动确认，这里改为手动确认消息
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置一个队列
        container.setQueueNames("fanout.queue.D");
        container.setMessageListener(myAckReceiverListener);
        return container;
    }

}
