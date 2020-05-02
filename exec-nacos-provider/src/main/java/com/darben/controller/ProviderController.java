package com.darben.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: darben
 * @create: 2020-05-01 21:09
 */
@RefreshScope
@RestController
@RequestMapping(value = "/provider")
public class ProviderController {

    @Value("${user.name.p}")
    private String userName;

    @Value("${user.age.p}")
    private String userAge;

    @Value("${mysql.uri}")
    private String mysqlVar;

    @Value("${exception.code.1001}")
    private String exceptionCode;

    @RequestMapping("/hello/get")
    public String getHelloNacosName(){
        System.out.println(userName+";"+userAge);
        System.out.println(mysqlVar+";");
        System.out.println(exceptionCode+";");
        return "hello nacos";
    }

}
