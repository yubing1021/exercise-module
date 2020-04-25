package com.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

/**
 * @description: 修改节点-同步方式
 * @author: darben
 * @create: 2020-03-24 15:56
 */
public class UpdateNodeSync extends ZkBaseServices{

    public static void main(String[] args) throws InterruptedException {

        UpdateNodeSync obj =new UpdateNodeSync();

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void handler() {

        try {
            //修改节点数据，cmd： set /sync-node1 7788
            Stat stat = zookeeper.setData("/sync-node1", "5566".getBytes(), -1);
            System.out.println("stat\t"+stat);

            /*
             Stat stat = zookeeper.setData("/sync-node1", "5566".getBytes(), -4);
             版本不对，即出现以下异常：
             org.apache.zookeeper.KeeperException$BadVersionException: KeeperErrorCode = BadVersion for /sync-node1
             */
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
