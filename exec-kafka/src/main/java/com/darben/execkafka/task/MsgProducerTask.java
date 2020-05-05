package com.darben.execkafka.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

/**
 * @description: 生产者定时任务
 * @author: darben
 * @create: 2020-03-16 22:20
 */
@Slf4j
@Component
@EnableScheduling
public class MsgProducerTask {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Scheduled(cron = "0/10 * * * * ?")
    public void send(){
        String message = UUID.randomUUID().toString();

        kafkaTemplate.send("test",message);

        log.info("消息发送成功 msg= {}",message);
        log.info("");
        /*listenableFuture.addCallback(o->{
            log.info("消息发送成功，{}", reactor.core.publisher.Mono.just(o));
        });*/
    }
}
