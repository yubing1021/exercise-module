package com.concurrent.blocking;

/**
 * @description: 消费者
 * @author: darben
 * @create: 2020-04-17 16:25
 */
public class Consumer extends Thread{

    private StoreService storeService;
    //每次消费的数量
    private int num;

    public Consumer(StoreService storeService){
        this.storeService = storeService;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        consume(num);
    }

    public void consume(int num){
        storeService.consume(num);
    }

}
