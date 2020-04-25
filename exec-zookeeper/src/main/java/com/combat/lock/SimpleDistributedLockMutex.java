package com.combat.lock;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @description: 简单的互斥锁->实现分布式锁接口
 * @author: darben
 * @create: 2020-03-27 17:46
 */
public class SimpleDistributedLockMutex extends BaseDistributedLock implements DistributedLock{

    private static final String LOCK_NAME = "lock-";
    private final String basePath;
    private String ourLockPath;

    public SimpleDistributedLockMutex(ZkClientExt client, String basePath) {
        super(client, basePath, LOCK_NAME);
        this.basePath = basePath;
    }

    private boolean internalLock(long time,TimeUnit unit) throws Exception{
        ourLockPath = attemptLock(time,unit);
        return ourLockPath!=null;
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
        releaseLock(ourLockPath);
    }
}
