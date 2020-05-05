package com.darben.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 直连型交换机
 * exchange type = direct
 * 需要指定路由键，需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配
 * @author: darben
 * @create: 2020-05-05 14:47
 */
@Configuration
public class DirectRabbitConfig {

    /**
    *@Description: 创建队列 起名为：productDirectQueue
    *@Param: 
    *@return: 
    *@date: 2020/5/5
    */
    @Bean
    public Queue productDirectQueue(){
        /**
         * 参数说明：
         * name：队列Queue的名称
         * durable：是否持久化，true-持久化，false-不持久化，持久化队列：会被存储在磁盘上，当服务器重启仍然存在；暂存队列：当前连接有效
         * exclusive：默认为false,只能被当前创建的连接使用，而且当连接关闭后队列即被删除，此参考优先级高于durable
         * autoDelete：是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除
         */
        return new Queue("productDirectQueue", true, false, false);
    }

    /**
    *@Description: 创建交换机，起名为productDirectExchange
    *@Param:
    *@return:
    *@date: 2020/5/5
    */
    @Bean
    public DirectExchange productDirectExchange(){
        return new DirectExchange("productDirectExchange",true,false);
    }

    /**
    *@Description: 将队列和交换机绑定, 并设置用于匹配键：productDirectRouting
    *@Param: 
    *@return: 
    *@date: 2020/5/5
    */
    @Bean
    public Binding bindingDirect(){
        return BindingBuilder.bind(productDirectQueue()).to(productDirectExchange()).with("productDirectRouting");
    }

    @Bean
    public DirectExchange lonelyDirectExchange(){
        return new DirectExchange("lonelyDirectExchange");
    }
}
