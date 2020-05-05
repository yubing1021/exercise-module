package com.darben.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: darben
 * @create: 2020-05-05 21:32
 */
@Component
@Slf4j
public class MyAckReceiverListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliverTag = message.getMessageProperties().getDeliveryTag();
        try {
            String content = message.toString();
            log.info("MyAckReceiver content:" + content);
            log.info("消费的主题消息来自："+message.getMessageProperties().getConsumerQueue());
            channel.basicAck(deliverTag, true);
        }
        catch (Exception e) {
            channel.basicReject(deliverTag, false);
            e.printStackTrace();
        }
    }
}
