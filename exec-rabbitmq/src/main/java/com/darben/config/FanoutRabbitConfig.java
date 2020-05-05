package com.darben.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 扇型交换机
 * Fanout 不处理路由键。你只需要简单的将队列绑定到交换机上。
 * 一个发送到该类型交换机的消息都会被广播到与该交换机绑定的所有队列上。
 * @author: darben
 * @create: 2020-05-05 16:57
 */
@Configuration
public class FanoutRabbitConfig {

    /**
     * 创建三个queue,分别为：fanout.queue.A、fanout.queue.B、fanout.queue.C
     * 将三个队列都绑定在交换机 fanoutExchange 上
     * 因为是扇型交换机, 路由键无需配置,配置也不起作用
     */
    @Bean
    public Queue fanoutQueueA(){
        return new Queue("fanout.queue.A");
    }

    @Bean
    public Queue fanoutQueueB(){
        return new Queue("fanout.queue.B");
    }

    @Bean
    public Queue fanoutQueueC(){
        return new Queue("fanout.queue.C");
    }

    @Bean
    public Queue fanoutQueueD(){
        return new Queue("fanout.queue.D");
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Binding bindingFanoutQueueA(){
        return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanoutQueueB(){
        return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanoutQueueC(){
        return BindingBuilder.bind(fanoutQueueC()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanoutQueueD(){
        return BindingBuilder.bind(fanoutQueueD()).to(fanoutExchange());
    }
}
