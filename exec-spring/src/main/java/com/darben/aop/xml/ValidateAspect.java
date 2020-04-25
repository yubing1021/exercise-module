package com.darben.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @description: 验证
 * @author: darben
 * @create: 2020-04-19 17:02
 */
public class ValidateAspect {

    public void beforeMethod(JoinPoint joinPoint){

        String methodName = joinPoint.getSignature().getName();

        System.out.println("validate method "+methodName+" args "+ Arrays.asList(joinPoint.getArgs()));
    }

}

