package com.concurrent.blocking;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-17 15:52
 */
public interface StoreService {

    /**
    *@Description: 生产
    *@Param:
    *@return:
    *@date: 2020/4/17
    */
    void produce(int num);

    /**
    *@Description: 消费
    *@Param:
    *@return:
    *@date: 2020/4/17
    */
    void consume(int num);

}
