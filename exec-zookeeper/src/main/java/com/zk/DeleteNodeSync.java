package com.zk;

import org.apache.zookeeper.KeeperException;

/**
 * @description: 删除节点->同步
 * @author: darben
 * @create: 2020-03-24 17:18
 */
public class DeleteNodeSync extends ZkBaseServices {

    public static void main(String[] args) throws InterruptedException {

        DeleteNodeSync obj = new DeleteNodeSync();

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void handler() {
        try {

            //删除无子节点的节点方法
            zookeeper.delete("/sync-node6", -1);
            System.out.println("/sync-node6 删除成功" );

            /*
            * 当节点存在子节点的时候，删除会报错：
            * org.apache.zookeeper.KeeperException$NotEmptyException: KeeperErrorCode = Directory not empty for /sync-node6
            *
            * */

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

}
