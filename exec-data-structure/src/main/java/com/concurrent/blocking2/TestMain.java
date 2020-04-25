package com.concurrent.blocking2;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-18 16:07
 */
public class TestMain {

    public static void main(String[] args) throws InterruptedException {
        IQueueService service = new SimpleQueueServiceImpl();

        for (int i=0; i<9; i++){
            service.put(i);
        }

        for (int i=0; i<20; i++){
            service.take();
        }
    }

}
