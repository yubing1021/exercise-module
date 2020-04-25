package com.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description: CGLib代理类
 * @author: darben
 * @create: 2020-04-15 18:17
 */
public class CglibProxy implements MethodInterceptor {

    //需要代理的目标对象
    private Object target;

    /**
    *@Description: 重写拦截方法
    *@Param:
    *@return:
    *@date: 2020/4/15
    */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLib动态代理，监听开始");
        Object invoke = method.invoke(target, objects);
        System.out.println("CGLib动态代理，监听结束");
        return invoke;
    }

    public Object getCglibProxy(Object targetObject){
        //为目标对象target赋值
        this.target = targetObject;
        Enhancer enhancer = new Enhancer();
        //设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(targetObject.getClass());
        //设置回调
        enhancer.setCallback(this);
        //创建并返回代理对象
        Object result = enhancer.create();
        return result;
    }
}
