package com.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: ReentrantLock 可重入锁
 * @author: darben
 * @create: 2020-04-16 10:46
 */
public class ReentrantLockTest {

    //默认非公平锁
    //private static final Lock lock= new ReentrantLock();
    //公平锁，FIFO
    private static final Lock lock= new ReentrantLock(true);

    public static void main(String[] args) {

        for (int i=0; i<10 ; i++){
            new Thread(()->test(),"线程"+i).start();
        }

    }

    public static void test(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"获取到了锁");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放了锁");
            lock.unlock();
        }
    }

}
