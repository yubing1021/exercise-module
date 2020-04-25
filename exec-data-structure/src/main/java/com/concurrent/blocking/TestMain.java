package com.concurrent.blocking;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-17 16:33
 */
public class TestMain {

    public static void main(String[] args) throws InterruptedException {
        StoreService storeService = new SimpleStoreServiceImpl();

        //生产者
        Producer p1 = new Producer(storeService);
        Producer p2 = new Producer(storeService);

        //消费者
        Consumer c1 = new Consumer(storeService);
        Consumer c2 = new Consumer(storeService);

        p1.setNum(10);
        p2.setNum(100);

        c1.setNum(20);;
        c2.setNum(20);

        p1.start();
        p2.start();

        c1.start();
        c2.start();

        TimeUnit.SECONDS.sleep(10);
        Consumer c3 = new Consumer(storeService);
        c3.setNum(5);
        c3.start();
    }

}
