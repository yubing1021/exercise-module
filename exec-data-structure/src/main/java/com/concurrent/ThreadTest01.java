package com.concurrent;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-16 21:35
 */
public class ThreadTest01 {

    private Map<String,Object> map = new HashMap<>();

    private boolean flag = true;

    public static void main(String[] args) {

        ThreadTest01 threadTest01 = new ThreadTest01();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" wait");

            threadTest01.observer();

            System.out.println(Thread.currentThread().getName()+" run");

        },"thread-a").start();

        /*try {
            Thread.sleep(2000);
            synchronized (threadTest01.map){
                threadTest01.map.notifyAll();
            }
            System.out.println(threadTest01.map.keySet());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+" notify start");

                Thread.sleep(200);
                threadTest01.info();
                System.out.println(threadTest01.map.keySet());

                System.out.println(Thread.currentThread().getName()+" notify end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"thread-b").start();

    }

    private void observer(){

        synchronized (map){
            try {
                map.put("1","1");
                map.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void info(){
        synchronized (map){
            map.notifyAll();
        }
    }

}
