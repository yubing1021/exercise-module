package com.proxy.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-15 17:06
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target){
        this.target = target;
    }

    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(IndexDao.class.getClassLoader(), new Class[]{IndexDao.class}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy start");
        System.out.println(method.getName());
        Object object = method.invoke(target, args);
        System.out.println("proxy end");
        return object;
    }
}
