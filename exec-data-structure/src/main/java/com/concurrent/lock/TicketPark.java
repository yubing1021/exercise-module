package com.concurrent.lock;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @description: park+自旋 实现加锁
 * @author: darben
 * @create: 2020-04-18 17:03
 */
public class TicketPark {
    //加锁标记
    private AtomicBoolean isLock = new AtomicBoolean(false);
    //票库存
    private int ticketCount=1000;
    //等待线程队列
    private final Queue<Thread> waitThreadQueue = new LinkedBlockingQueue<>();

    //抢票
    public void bye(){
        while (!lock()){
            //获取不到锁的线程,添加到队列，并休眠
            lockWait();
        }

        //获取到锁的线程执行的逻辑
        String name = Thread.currentThread().getName();
        //加锁成功，执行业务逻辑
        System.out.println(name + ":加锁成功...");
        System.out.println(name + ":开始抢票...");
        ticketCount--;
        System.out.println(name + ":抢到了，库存:" + ticketCount);
        System.out.println(name + ":释放锁.");

        //释放锁
        unlock();
    }

    public boolean lock(){
        return isLock.compareAndSet(false,true);
    }

    public void unlock(){
        isLock.set(false);
        //唤醒队列中的第一个线程
        LockSupport.unpark(waitThreadQueue.poll());
    }

    public void lockWait(){
        //将取不到锁的线程添加到队列
        waitThreadQueue.add(Thread.currentThread());
        //休眠
        LockSupport.park();
    }

    public static void main(String[] args) {
        TicketPark ticketPark = new TicketPark();

        for(int i=0; i<100; i++){
            new Thread(()->{
                ticketPark.bye();
            },"线程-"+i).start();
        }
    }

}
