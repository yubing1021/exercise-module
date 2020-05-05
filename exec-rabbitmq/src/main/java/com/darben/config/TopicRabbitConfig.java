package com.darben.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: Topic交换机
 * exchange type=topic
 * 将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。符号“#”匹配一个或多个词，符号“*”只能匹配一个词。
 * @author: darben
 * @create: 2020-05-05 15:29
 */
@Configuration
public class TopicRabbitConfig {

    public final static String man = "topic.man";
    public final static String woman = "topic.woman";

    @Bean
    public Queue manQueue(){
        return new Queue(TopicRabbitConfig.man);
    }

    @Bean
    public Queue womanQueue(){
        return new Queue(TopicRabbitConfig.woman);
    }

    /**
    *@Description: 创建主题交换机，名称为：topicExchange
    *@Param:
    *@return: 
    *@date: 2020/5/5
    */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    /**
     *@Description:
     * 将manQueue与topicExchange进行绑定，绑定的键值为topic.man
     * 这样只要是消息携带的路由键是topic.man,才会分发到该队列
     *@Param:
     *@return:
     *@date: 2020/5/5
    */
    @Bean
    public Binding bindingManExchangeMessage(){
        return BindingBuilder.bind(manQueue()).to(topicExchange()).with(man);
    }

    /**
     *@Description:
     * 将womanQueue与topicExchange进行绑定，并且绑定的键值为通配路由键规则topic.#
     * 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
     *@Param:
     *@return:
     *@date: 2020/5/5
    */
    @Bean
    public Binding bindingWomanExchangeMessage(){
        return BindingBuilder.bind(womanQueue()).to(topicExchange()).with("topic.#");
    }



}
