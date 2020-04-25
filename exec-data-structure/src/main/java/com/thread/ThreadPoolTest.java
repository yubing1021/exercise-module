package com.thread;

import java.util.concurrent.*;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-06 20:16
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        //Executors框架创建的5类型线程池
        //1.有任务的时候去创建线程，保留60s
        ExecutorService e1 = Executors.newCachedThreadPool();

        //创建固定数量的线程池
        ExecutorService e2 = Executors.newFixedThreadPool(1);

        //线程池能按照时间计划来执行任务
        ExecutorService e3 = Executors.newScheduledThreadPool(1);

        //单个的线程池
        ExecutorService e4 = Executors.newSingleThreadExecutor();

        ExecutorService e5 = Executors.newSingleThreadScheduledExecutor();

        BlockingQueue blockingQueue = new LinkedBlockingDeque();
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
                5,         //核心线程数
                5,     //最大线程数
                6000l,       //超时时间
                TimeUnit.MILLISECONDS,  //单位
                blockingQueue,  //等待执行的任务队列
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        threadPoolExecutor.execute(()->{

        });

        Future future = new FutureTask(()->{
            return 1;
        });

        try {
           int i = (int) future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
