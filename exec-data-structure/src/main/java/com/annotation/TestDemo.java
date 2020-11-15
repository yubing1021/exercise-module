package com.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @description:
 * @author: darben
 * @create: 2020-09-26 18:03
 */
@Description(value = "this is desc")
public class TestDemo {

    @Name(originate = "创始人：余兵", community = "linzhi")
    public String getName() {
        return null;
    }

    @Name(originate = "aa", community = "bb")
    public String getName2() {
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        String className = "com.annotation.TestDemo";
        Class test = Class.forName(className);
        Method[] methods = test.getMethods();
        boolean flags = test.isAnnotationPresent(Description.class);
        if (flags) {
            Description des = (Description) test.getAnnotation(Description.class);
            System.out.println(des.value());
        }

        Arrays.stream(methods).forEach(e -> {
            if (e.isAnnotationPresent(Name.class)) {
                Name name = e.getAnnotation(Name.class);
                System.out.println(name.originate());
                System.out.println(name.community());
            }
        });
    }
}
