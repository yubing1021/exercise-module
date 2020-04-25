package com.zk.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @description: zk demo
 * 建立zk连接
 * @author: darben
 * @create: 2020-03-23 10:33
 */
public class ZookeeperTestConnection implements Watcher {

    //其实可以把它看作一个计数器，只不过这个计数器的操作是原子操作，同时只能有一个线程去操作这个计数器，也就是同时只能有一个线程去减这个计数器里面的值。
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void process(WatchedEvent watchedEvent) {

        System.out.println("receive the event:"+watchedEvent);
        if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(ZookeeperConfig.ADDRESS,5000,new ZookeeperTestConnection());

            System.out.println(zooKeeper.getState());

            countDownLatch.await();

            System.out.println("zookeeper session established");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
