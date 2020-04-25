package com.zk.config;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @description: zk 同步创建临时节点
 * @author: darben
 * @create: 2020-03-23 16:05
 */
public class ZookeeperTestCreateNodeSync implements Watcher {

    private static final CountDownLatch countDownLatch =new CountDownLatch(1);

    //测试节点前缀
    private static final String PREFIX_SYNC="/mytest-sync-create-";

    @Override
    public void process(WatchedEvent watchedEvent) {
        //连上了
        if(Event.KeeperState.SyncConnected ==  watchedEvent.getState()){
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(ZookeeperConfig.ADDRESS,5000,new ZookeeperTestCreateNodeSync());

            System.out.println(zooKeeper.getState());
            //等待连接
            countDownLatch.await();

            //创建节点操作
            /* CreateMode枚举说明
             *  PERSISTENT : 持久节点
             *  PERSISTENT_SEQUENTIAL : 持久顺序节点
             *  EPHEMERAL : 临时节点
             *  EPHEMERAL_SEQUENTIAL : 临时顺序节点
             */

            //1.同步创建一个临时节点
            String path1= zooKeeper.create(PREFIX_SYNC,"temped".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("success create node:\t"+path1);

            //2.同步创建2个临时顺序节点
            String path2 = zooKeeper.create(PREFIX_SYNC, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("success create node:\t"+path2);

            String path3 = zooKeeper.create(PREFIX_SYNC, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("success create node:\t"+path3);

            //3.同步创建一个持久节点，ACL为 world:anyone:cdrwa 等同于如下命令：
            //create /node 123 world:anyone:cdrwa
            //String path3 =zooKeeper.create(PREFIX_SYNC, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            //System.out.println("success create node:\t"+path3);

            while (true){
                Thread.sleep(10000);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
