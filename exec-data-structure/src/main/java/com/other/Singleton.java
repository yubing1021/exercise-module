package com.other;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-06 20:10
 */
public class Singleton {

    private static volatile Singleton instance;

    private Singleton(){
    }

    public static Singleton getInstance(){
        if(instance==null){

            synchronized (Singleton.class){
                if(instance==null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
