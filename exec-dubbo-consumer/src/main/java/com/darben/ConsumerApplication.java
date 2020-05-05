package com.darben;

import com.darben.service.ProductService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-25 17:31
 */
public class ConsumerApplication {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-consumer.xml");
        context.start();
        System.out.println("consumer start");
        ProductService productService = context.getBean(ProductService.class);
        System.out.println("consumer");
        System.out.println(productService.getPermissions(1L));

    }
}
