package com.thread;

/**
 * @description: 线程优先级测试
 * @author: darben
 * @create: 2020-04-19 11:00
 */
public class ThreadPriorityTest {

    public static void main(String[] args) {

        MyThread t1 = new MyThread("thread-2",2);

        MyThread t2 = new MyThread("thread-6",6);

        MyThread t3 = new MyThread("thread-7",7);

        MyThread t4 = new MyThread("thread-1",1);

        t1.start();

        t2.start();

        t3.start();

        t4.start();

    }

}

class MyThread extends Thread{

    //线程优先级，级别越大越先执行 0-10，默认5
    private int priority;

    public MyThread(String name, int priority){
        super(name);
        this.priority = priority;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        for (int i=0; i<20; i++){
            System.out.println(Thread.currentThread().getName()+"\t"+i);
        }
    }

}
