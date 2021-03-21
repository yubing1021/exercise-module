package com.darben;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class ExecSpringbootAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExecSpringbootAdminApplication.class, args);
    }

}
