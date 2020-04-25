package com.combat.lock;

import java.util.concurrent.TimeUnit;

/**
*@Description: 分布式锁接口
*@Param:
*@return:
*@date: 2020/3/27
*/
public interface DistributedLock {
    
    /**
    *@Description: 获取锁，如果没有获取到就等待
    *@Param:
    *@return:
    *@date: 2020/3/27
    */
    public void acquire() throws Exception;

    /**
    *@Description: 获取锁，直到超时
    *@Param: 
    *@return: 
    *@date: 2020/3/27
    */
    public boolean acquire(long time, TimeUnit unit) throws Exception;

    /**
    *@Description: 释放锁
    *@Param:
    *@return:
    *@date: 2020/3/27
    */
    public void release() throws Exception;
}
