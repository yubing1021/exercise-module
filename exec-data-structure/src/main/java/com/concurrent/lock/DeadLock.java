package com.concurrent.lock;

/**
 * @description: 线程死锁
 * @author: darben
 * @create: 2020-04-20 10:59
 */
public class DeadLock {

    public static void main(String[] args) {
        DieLock d1 = new DieLock(true);
        DieLock d2 = new DieLock(false);
        Thread t1 = new Thread(d1);
        Thread t2 = new Thread(d2);
        t1.start();
        t2.start();
    }

}

class MyLock{
    public static Object obj1 = new Object();
    public static Object obj2 = new Object();
}

class DieLock implements Runnable{

    private boolean flag;

    public DieLock(boolean flag){
        this.flag = flag;
    }

    @Override
    public void run() {
        if(flag){
            while (true){
                synchronized (MyLock.obj1){
                    System.out.println(Thread.currentThread().getName()+"...if...obj1");
                    synchronized (MyLock.obj2){
                        System.out.println(Thread.currentThread().getName()+"...if...obj2");
                    }
                }
            }
        }
        else{
            while (true){
                synchronized (MyLock.obj2){
                    System.out.println(Thread.currentThread().getName()+"...else...obj2");
                    synchronized (MyLock.obj1){
                        System.out.println(Thread.currentThread().getName()+"...else...obj1");
                    }
                }
            }

        }

    }
}
