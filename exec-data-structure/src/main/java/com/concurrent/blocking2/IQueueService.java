package com.concurrent.blocking2;

/**
 * @description: 阻塞队列
 * @author: darben
 * @create: 2020-04-17 16:56
 */
public interface IQueueService<T>{

    /**
     *@Description:
     * 将给定的元素设置到队列中，如果设置成功返回true, 否则返回false. e的值不能为空，否则抛出空指针异常。
     * 非阻塞
     *@Param:
     *@return:
     *@date: 2020/4/18
    */
    boolean offer(T t) throws InterruptedException;

    /**
     *@Description:
     * 将元素设置到队列中，如果队列中没有多余的空间，该方法会一直阻塞，直到队列中有多余的空间
     * 阻塞
     *@Param:
     *@return:
     *@date: 2020/4/18
    */
    void put(T t) throws InterruptedException;

    /**
     *@Description:
     * 从队列中获取值，如果没值，直接返回NULL
     *@Param:
     *@return:
     *@date: 2020/4/18
    */
    T poll() throws InterruptedException;

    /**
     *@Description:
     * 从队列中获取值，如果队列中没有值，线程会一直阻塞，直到队列中有值，并且该方法取得了该值。
     *@Param:
     *@return:
     *@date: 2020/4/18
    */
    T take() throws InterruptedException;
}
