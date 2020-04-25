package com.combat.lock;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-29 14:08
 */
public class DistributedLockMutex extends BaseDistributedLock implements DistributedLock {

    private static final String LOCK_PREFIX = "lock-";
    private final ConcurrentHashMap<Thread,LockData> threadData = new ConcurrentHashMap<>();
    private final String basePath;

    private static class LockData{
        final String lockPath;
        //原子计数器
        final AtomicInteger lockCount = new AtomicInteger(1);
        private LockData(Thread owningThread, String lockPath){
            this.lockPath = lockPath;
        }
    }

    public DistributedLockMutex(ZkClientExt client, String basePath){
        super(client,basePath,LOCK_PREFIX);
        this.basePath = basePath;
    }

    private boolean internalLock(long time, TimeUnit timeUnit) throws Exception {
        Thread currentThread = Thread.currentThread();

        LockData lockData = threadData.get(currentThread);
        if(lockData!=null){
            //获取锁，计数器递增
            lockData.lockCount.incrementAndGet();
            return true;
        }

        String lockPath = attemptLock(time,timeUnit);
        if(lockPath!=null){
            LockData newLockData = new LockData(currentThread,lockPath);
            threadData.put(currentThread,newLockData);
            return true;
        }
        return false;
    }

    @Override
    public void acquire() throws Exception {
        if(!internalLock(-1,null)){
            throw new IOException("连接丢失!在路径:'"+basePath+"'下不能获取锁!");
        }
    }

    @Override
    public boolean acquire(long time, TimeUnit unit) throws Exception {
        return internalLock(time,unit);
    }

    @Override
    public void release() throws Exception {
        Thread currentThread = Thread.currentThread();
        LockData lockData = threadData.get(currentThread);
        if(lockData==null){
            throw new IllegalMonitorStateException("你不是锁: " + basePath + "的拥有者,无法执行此操作！");
        }

        //计数器递减
        int newLockCount = lockData.lockCount.decrementAndGet();
        if(newLockCount>0){
            return;
        }
        if(newLockCount<0){
            throw new IllegalMonitorStateException("锁计数器已经为负数: " + basePath);
        }

        try {
            releaseLock(lockData.lockPath);
        }
        catch (Exception e){

        }finally {
            threadData.remove(currentThread);
        }
    }
}
