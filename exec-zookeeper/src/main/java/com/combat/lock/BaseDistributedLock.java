package com.combat.lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @description: 基类：与zookeeper交互的封装
 * @author: darben
 * @create: 2020-03-27 17:46
 */
public class BaseDistributedLock {

    private final ZkClientExt client;
    //子节点（锁节点）路径，不带有序序号，有序序号系统生成
    private final String path;
    //根节点路径
    private final String basePath;
    private final String lockName;
    private static final Integer MAX_RETRY_COUNT =10;

    public BaseDistributedLock(ZkClientExt client, String path, String lockName){
        this.client = client;
        this.basePath = path;
        this.path = path.concat("/").concat(lockName);
        this.lockName = lockName;
    }

    /**
    *@Description:
    *@Param: 
    *@return: 
    *@date: 2020/3/29
    */
    private void  deleteOurPath(String ourPath) throws Exception{
        client.delete(ourPath);
    }

    /**
    *@Description: 创建临时有序节点
    *@Param:
    *@return:
    *@date: 2020/3/29
    */
    private String createLockNode(ZkClient client, String path) throws Exception{
        return client.createEphemeralSequential(path,null);
    }

    /**
    *@Description: 等待获取锁资源
    *@Param:
    *@return:
    *@date: 2020/3/29
    */
    private boolean waitToLock(Long startMillis, Long millisToWait, String ourPath) throws Exception{

        //获取到锁的标识
        boolean haveTheLock = false;
        //
        boolean doDelete = false;

        try {
            //循环，直到获取到锁资源
            while (!haveTheLock){
                //有序子节点为00001、0002
                List<String> children = getSortChildren();

                //获取当前客户端有序节点名称
                String sequenceNodeName = ourPath.substring(basePath.length()+1);

                //获取索引序号
                int ourIndex  = children.indexOf(sequenceNodeName);
                if(ourIndex<0){
                    throw new ZkNoNodeException("节点没有找到："+ourPath);
                }

                //当index==0的时候，表示是最小顺序节点，则获取到锁权限
                boolean isGetTheLock = (ourIndex==0);
                //监听的节点名称
                String pathToWatch = isGetTheLock?null:children.get(ourIndex-1);
                if(isGetTheLock){
                    haveTheLock =true;
                }
                else{
                    //监听节点变更信息
                    String previousSequencePath = basePath.concat("/").concat(pathToWatch);
                    final CountDownLatch latch = new CountDownLatch(1);

                    final IZkDataListener previousListener = new IZkDataListener() {
                        @Override
                        public void handleDataChange(String s, Object o) throws Exception {
                            //ignore
                        }

                        @Override
                        public void handleDataDeleted(String s) throws Exception {
                            latch.countDown();
                        }
                    };

                    //注册监听事件
                    client.subscribeDataChanges(previousSequencePath, previousListener);

                    if(millisToWait!=null){
                        millisToWait = millisToWait - (System.currentTimeMillis()-startMillis);
                        startMillis = System.currentTimeMillis();
                        if(millisToWait<=0){
                            doDelete = true;
                            break;
                        }
                        //等待
                        latch.await(millisToWait, TimeUnit.MICROSECONDS);
                    }
                    else{
                        latch.await();
                    }
                }
            }
        }catch (Exception e){
            doDelete = true;
            throw e;
        }finally {
            if(doDelete){
                deleteOurPath(ourPath);
            }
        }
        return haveTheLock;
    }

    /**
    *@Description: 释放锁资源
    *@Param:
    *@return:
    *@date: 2020/3/29
    */
    protected void releaseLock(String lockPath) throws Exception{
        deleteOurPath(lockPath);
    }

    /**
    *@Description: 获取锁资源
    *@Param: 
    *@return: 
    *@date: 2020/3/29
    */
    protected String attemptLock(long time, TimeUnit timeUnit) throws Exception {
        final Long startMillis = System.currentTimeMillis();
        final Long millsToWait = (time!=-1&&timeUnit!=null)?timeUnit.toMillis(time):null;

        String ourPath = null;
        boolean hasTheLock = false;
        boolean isDone = false;
        int retryCount = 0;

        //循环处理，直到完成
        while (!isDone){
            isDone = true;
            try {
                //1.创建临时有序节点
                ourPath =createLockNode(client,path);

                //2.获取锁
                hasTheLock = waitToLock(startMillis,millsToWait,ourPath);
            } catch (ZkNoNodeException e){
                //创建父节点
                client.createPersistent(basePath,true);

                //如果重试次数小于默认定义次数，再进行重试
                if(retryCount++<MAX_RETRY_COUNT){
                    isDone = false;
                }
                else{
                    throw e;
                }
            }
        }

        //如果获取到了锁，则返回临时有序节点路径，否则返回null
        if(hasTheLock){
            return ourPath;
        }
        else {
            return null;
        }
    }

    /**
    *@Description: 进行升序排序
    *@Param:
    *@return:
    *@date: 2020/3/29
    */
    private List<String> getSortChildren() throws Exception{

        try {
            List<String> children = client.getChildren(basePath);

            List<String> sortChildren = children.stream().sorted().collect(Collectors.toList());

            return sortChildren;
        }catch (ZkNoNodeException e){
            client.createPersistent(basePath,true);
            return getSortChildren();
        }
    }

}
