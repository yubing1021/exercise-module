package com.concurrent.blocking;

/**
 * @description: 生产者
 * @author: darben
 * @create: 2020-04-17 16:25
 */
public class Producer extends Thread {

    private StoreService storeService;

    //每次生产的数量
    private int num;

    public Producer(StoreService storeService){
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
        produce(num);
    }

    public void produce(int num){
        storeService.produce(num);
    }

}
