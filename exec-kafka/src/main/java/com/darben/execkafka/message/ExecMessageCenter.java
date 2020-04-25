package com.darben.execkafka.message;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @description: 消息处理中心
 *               消费数据
 * @author: darben
 * @create: 2020-03-16 16:46
 */
@Slf4j
@Component
public class ExecMessageCenter {

    @KafkaListener(topics = {"test"},groupId = "application-test",topicPartitions = {})
    public void listener(ConsumerRecord<String,Object> record,
                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                         Consumer consumer,
                         Acknowledgment ack){
        log.info("=> 单独消费者消费消息,topic= {} ,content = {}", topic, record.value());
        ack.acknowledge();
    }
}
