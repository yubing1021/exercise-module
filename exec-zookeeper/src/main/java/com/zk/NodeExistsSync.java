package com.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

/**
 * @description: 判断节点是否存在-同步方式
 * @author: darben
 * @create: 2020-03-24 21:38
 */
public class NodeExistsSync extends ZkBaseServices{

    public static void main(String[] args) throws InterruptedException {

        NodeExistsSync obj = new NodeExistsSync();

        Thread.sleep(Integer.MAX_VALUE);

    }

    @Override
    public void handler() {

        try {
            //判断节点是否存在，并且监听
            Stat exists = zookeeper.exists("/sync-node8", true);
            System.out.println(exists);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
