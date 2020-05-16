package com.darben;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@EnableDubbo(scanBasePackages = "com.darben")
@SpringBootApplication
public class ExecDubboApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExecDubboApiApplication.class, args);
        log.info("SpringApplication run success !");
    }

}
