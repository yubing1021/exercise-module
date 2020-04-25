package com.darben.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

/**
 * @description: 切面-通知
 * 可以使用@Order注解指定切面的优先级，值越小优先级越高
 *
 * @author: darben
 * @create: 2020-04-19 16:04
 */
public class LoggingAspect {

    /**
     * 前置通知
     * 在接口实现类的每个方法开始之前执行的一段代码
    */
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("The method "+methodName+" begins with "+ Arrays.asList(args));
    }

    /**
     *后置通知
     *在方法执行之后执行的一段代码，无论该方法是否出现异常
    */
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method "+methodName+" ends");
    }

    /**
     * 返回通知
     * 在方法正常结束执行的代码，可获取返回的结果
    */
    public void afterReturning(JoinPoint joinPoint, Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method "+methodName+" ends with "+ result);
    }

    /**
     * 异常通知
     * 在目标方法出现异常时会执行的代码
     * 可以访问到异常对象，且可以指定在出现特定异常是再执行通知代码
    */
    public void afterThrowing(JoinPoint joinPoint, Exception ex){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method "+methodName+" occurs exception： "+ ex);
    }

    /**
     * 环绕通知
     * 需要携带ProceedingJoinPoint类型的参数
     * 自己理解：其实即使实现了代理类invoke方法
     *
    */
    public Object aroundMethod(ProceedingJoinPoint pjd){

        Object result =null;
        String methodName = pjd.getSignature().getName();
        Object[] args = pjd.getArgs();
        //执行目标方法
        try {
            //前置通知
            System.out.println("The method "+methodName+ " begins with" + Arrays.asList(args));
            //执行目标方法
            result = pjd.proceed();
            //返回通知
            System.out.println("The method "+methodName+ " ends with" + Arrays.asList(args));
        } catch (Throwable throwable) {
            System.out.println("The method "+methodName+ " occur exception:" + throwable);
            return new RuntimeException(throwable);
        }
        //后置通知
        System.out.println("The method "+methodName+ " ends");

        return result;
    }

}
