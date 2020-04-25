package com.datast;

public interface TestInterface {
    final String varName = "testInterface";

    public void exec();

    default void init1(){
        System.out.println("111");
    }

    default void init(){
        System.out.println("init");
    }

}
