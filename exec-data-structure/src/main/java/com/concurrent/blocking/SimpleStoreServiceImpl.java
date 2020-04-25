package com.concurrent.blocking;

import java.util.LinkedList;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-17 15:55
 */
public class SimpleStoreServiceImpl implements StoreService {

    //仓库最大容量
    private final int MAX_SIZE=100;
    //仓库存储的载体，数据
    private LinkedList list = new LinkedList();

    @Override
    public void produce(int num) {

        synchronized (list){

            while (list.size()+num>MAX_SIZE){
                System.out.println("【要生产的数量】:"+num+"\t【库存量】："+list.size()+"\t超过【总量】："+MAX_SIZE+"\t暂停任务");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i=0; i<num; i++){
                list.add(new Object());
            }

            System.out.println("【已生产数量】："+num+"\t【现仓库数量】："+list.size());
            //唤醒所有wait线程
            list.notifyAll();
        }
    }

    @Override
    public void consume(int num) {

        synchronized (list){
            while ((list.size()-num)<0){
                System.out.println("【要消费的数量】:"+num+"\t【库存量】："+list.size()+"\t不够，暂停任务");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i=0; i<num; i++){
                list.remove();
            }

            System.out.println("【已消费数量】："+num+"\t【现仓库数量】："+list.size());
            //唤醒所有wait线程
            list.notifyAll();
        }

    }

}
