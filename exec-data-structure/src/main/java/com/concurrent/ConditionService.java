package com.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-17 21:12
 */
public class ConditionService {

    private static int nextThread =1;
    private ReentrantLock lock = new ReentrantLock();
    // 有三个线程，所以注册三个Condition
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    private AtomicInteger a = new AtomicInteger(0);
    private AtomicInteger b = new AtomicInteger(0);
    private AtomicInteger c = new AtomicInteger(0);

    public void executeA(){
        try {
            lock.lock();
            while (nextThread!=1){
                conditionA.await();
            }

            System.out.println(Thread.currentThread().getName()+" 工作" + a.getAndAdd(1));
            nextThread = 2;
            conditionB.signalAll();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public void executeB(){
        try {
            lock.lock();
            while (nextThread!=2){
                conditionB.await();
            }

            System.out.println(Thread.currentThread().getName()+" 工作"+ b.getAndAdd(1));
            nextThread = 3;
            conditionC.signalAll();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public void executeC(){
        try {
            lock.lock();
            while (nextThread!=3){
                conditionC.await();
            }

            System.out.println(Thread.currentThread().getName()+" 工作"+c.getAndAdd(1));
            nextThread = 1;
            conditionA.signalAll();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionService service = new ConditionService();

        new Thread(()->{
            for (int i=0; i<20; i++){
                service.executeB();
            }
        },"B").start();

        new Thread(()->{
            for (int i=0; i<20; i++){
                service.executeA();
            }
        },"A").start();

        new Thread(()->{
            for (int i=0; i<20; i++){
                service.executeC();
            }
        },"C").start();
    }

}
