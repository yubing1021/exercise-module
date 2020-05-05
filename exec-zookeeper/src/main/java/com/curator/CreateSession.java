package com.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryUntilElapsed;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-26 15:58
 */
public class CreateSession {

    public static final String ADDRESS="192.168.8.196:12181,192.168.8.196:12182,192.168.8.196:12183";

    public static void main(String[] args) throws InterruptedException {

        /*
         * 参数依次为
         * zk的连接地址、会话超时时间、连接超时时间、重试策略（有些操作会失败，允许对失败进行重试）
         */
        //重试策略1：指定重试的次数，每次重试的时间逐渐增加
        //RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,5);
        //
        // 重试策略2：表示重试次数是5，每次时间间隔1秒
        //RetryPolicy retryPolicy = new RetryNTimes(5,1000);

        //重试策略3：重试的最长时间为50秒，每次重试的时间间隔是1秒
        RetryPolicy retryPolicy = new RetryUntilElapsed(50000,1000);


        //创建客户端方法1
        /*CuratorFramework client = CuratorFrameworkFactory.
                newClient(address,5000,5000,retryPolicy);*/

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(ADDRESS)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();

        client.start();

        System.out.println(client.getState());

        Thread.sleep(Integer.MAX_VALUE);
    }
}
