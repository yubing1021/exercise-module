package com.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 通过加锁，保证共享资源数据的准确性
 * @author: darben
 * @create: 2020-04-16 21:17
 */
public class ThreadTest {

    private static int atomic=0;

    private static final Lock lock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {

        for(int i=0; i<10000; i++){
            new Thread(()->{
                ThreadTest.lockIncrAtomic();
            }, "thread-"+i).start();
        }

        TimeUnit.SECONDS.sleep(20);
        System.out.println("========================");
        System.out.println("atomic:"+ThreadTest.atomic);
    }

    public static void lockIncrAtomic(){
        lock.lock();
        ThreadTest.atomic++;
        System.out.println(Thread.currentThread().getName()+" "+ThreadTest.atomic);
        lock.unlock();
    }

    public synchronized static void syncIncrAtomic(){
        ThreadTest.atomic++;
        System.out.println(Thread.currentThread().getName()+" "+ThreadTest.atomic);
    }

}
