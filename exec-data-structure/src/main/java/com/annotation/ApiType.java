package com.annotation;

import java.lang.annotation.*;

/**
 * SOURCE 代表的是这个Annotation类型的信息只会保留在程序源码里
 * ClASS 意思是这个Annotation类型的信息保留在程序源码里
 * RUNTIME 表示在源码、编译好的.class文件中保留信息，在执行的时候会把这一些信息加载到JVM中去的
 */
@Retention(RetentionPolicy.CLASS)
/**
 * 作用的目标范围
 */
@Target({ElementType.METHOD})
/**
 *
 */
@Documented
public @interface ApiType {

    TypeEnum type();

    String describe() default "";
}
