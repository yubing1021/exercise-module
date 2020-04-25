package com.darben.execsubeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ExecSubEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExecSubEurekaApplication.class, args);
    }

}
