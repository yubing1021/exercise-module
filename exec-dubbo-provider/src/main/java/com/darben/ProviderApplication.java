package com.darben;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-25 17:23
 */
public class ProviderApplication {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-provider.xml");
        System.out.println(context.getDisplayName()+":here");
        context.start();
        System.out.println("服务已经启动");
        System.in.read();
    }

}
