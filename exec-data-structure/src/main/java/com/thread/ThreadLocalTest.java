package com.thread;

import io.netty.util.concurrent.FastThreadLocal;

/**
 * @description: 线程的本地变量
 * @author: darben
 * @create: 2020-04-08 14:36
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    private static FastThreadLocal<Integer> fastThreadLocal = new FastThreadLocal<>();

    public static void main(String[] args) {

        new Thread(()->{
            for(int i=0; i<=10; i++){
                try {
                    fastThreadLocal.set(i);
                    System.out.println(Thread.currentThread().getName()+"="+fastThreadLocal.get());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    fastThreadLocal.remove();
                }
            }
        },"thread1").start();


        new Thread(()->{
            for(int i=0; i<=10; i++){
                try {
                    System.out.println(Thread.currentThread().getName()+"="+fastThreadLocal.get());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"thread2").start();

    }

}
