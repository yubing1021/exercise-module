package com.darben.aop.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-19 15:42
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-xml.xml");

        //Calculator calculator = ctx.getBean(CalculatorImpl.class);
        Calculator calculator = (Calculator) ctx.getBean("calculator");

        System.out.println(calculator.getClass().getName());

        int result = calculator.add(1,2);
        System.out.println("result:\t"+result);

        /*System.out.println();

        result = calculator.mul(10,10);
        System.out.println("result:\t"+result);

        System.out.println();

        result = calculator.div(10,0);
        System.out.println("result:\t"+result);*/
    }
}
