package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 线程池，设置线程执行的优先级
 * @author: darben
 * @create: 2020-04-19 11:16
 */
public class ThreadPoolPriorityTest {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(10);

        MyThreadTmp t1 = new MyThreadTmp("thread-1", 1);
        MyThreadTmp t3 = new MyThreadTmp("thread-3", 3);
        MyThreadTmp t5 = new MyThreadTmp("thread-5", 5);
        MyThreadTmp t7 = new MyThreadTmp("thread-7", 7);
        MyThreadTmp t9 = new MyThreadTmp("thread-9", 9);

        pool.execute(t1);
        pool.execute(t3);
        pool.execute(t5);
        pool.execute(t7);
        pool.execute(t9);
    }
}

class MyThreadTmp extends Thread{

    private int priority;

    private String name;

    public MyThreadTmp(String name,int priority){
        this.name = name;
        this.priority = priority;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        for (int i=0; i<20; i++){
            System.out.println(name+"\t"+i);
        }
    }
}
