package com.concurrent.blocking2;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-18 15:48
 */
public class SimpleQueueServiceImpl implements IQueueService<Object> {

    //定义一个链表集合
    private List list = new LinkedList();

    //长度，容量
    private static final int MAX_SIZE = 10;

    @Override
    public boolean offer(Object o) throws InterruptedException {
        return false;
    }

    @Override
    public Object poll() throws InterruptedException {
        return null;
    }


    @Override
    public void put(Object o) throws InterruptedException {
        synchronized (list){
            //判断集合数据长度
            while ((list.size()+1) > MAX_SIZE){
                list.wait();
            }

            list.add(o);
            System.out.println("添加元素成功，【当前集合大小】："+list.size());
            list.notifyAll();
        }
    }

    @Override
    public Object take() throws InterruptedException {
        synchronized (list){

            while ((list.size()-1)<0){
                list.wait();
            }

            Object object = list.get(list.size()-1);
            list.remove(object);
            System.out.println("获取元素成功，【当前集合大小】："+list.size());
            list.notifyAll();
            return object;
        }
    }

}
