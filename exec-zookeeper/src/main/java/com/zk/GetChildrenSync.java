package com.zk;

import org.apache.zookeeper.KeeperException;

import java.util.List;

/**
 * @description: 获取子节点集合->同步方式
 * @author: darben
 * @create: 2020-03-24 16:14
 */
public class GetChildrenSync extends ZkBaseServices{

    public static void main(String[] args) throws InterruptedException {

        GetChildrenSync obj = new GetChildrenSync();

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void handler() {

        try {

            //第二个参数的意思是：
            //是否需要关注子节点的变化，注册监听器，是-true,不是-false
            List<String> children = zookeeper.getChildren("/", true);
            System.out.println(children);

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
