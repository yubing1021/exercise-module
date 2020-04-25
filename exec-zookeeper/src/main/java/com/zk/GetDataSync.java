package com.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

/**
 * @description: 查询节点数据->同步
 * @author: darben
 * @create: 2020-03-24 16:39
 */
public class GetDataSync extends ZkBaseServices{

    public static void main(String[] args) throws InterruptedException {

        GetDataSync obj = new GetDataSync();

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void handler() {

        try {
            byte[] data = zookeeper.getData("/sync-node1", true, new Stat());
            System.out.println("data:/t" + new String(data));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
