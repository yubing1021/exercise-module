package com.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

/**
 * @description: 创建节点->同步方式
 * @author: darben
 * @create: 2020-03-24 13:03
 */
public class CreateNodeSync extends ZkBaseServices {

    public static void main(String[] args) throws InterruptedException {
        CreateNodeSync obj = new CreateNodeSync();

        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
    *@Description:
    *@Param:
    *@return:
    *@date: 2020/3/24
    */
    @Override
    public void handler() {

        try {

            /*
            * 说明：
            * ZooDefs.Ids ACL权限列表
            *  OPEN_ACL_UNSAFE 对所有用户开放权限
            *  CREATOR_ALL_ACL
            *  READ_ACL_UNSAFE 只读权限
            *
            * CreateMode 模式
            *  PERSISTENT 永久节点
            *  PERSISTENT_SEQUENTIAL 永久有序节点
            *  EPHEMERAL 临时节点
            *  EPHEMERAL_SEQUENTIAL 临时有序节点
            *
            * */

            //创建永久节点
            //create /exec-zk-node1
            int df_incr=1;
            String df_content="this is data content ";
            //永久节点 cmd: create /sync-node2
            String path01 = zookeeper.create("/sync-node1", (df_content+df_incr++).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(path01);

            //永久有序节点 cmd: create -s /sync-node2
            String path02 = zookeeper.create("/sync-node2",(df_content+df_incr++).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT_SEQUENTIAL);
            String path03 = zookeeper.create("/sync-node2",(df_content+df_incr++).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT_SEQUENTIAL);
            System.out.println(path02);
            System.out.println(path03);

            //临时节点 cmd: create -e /sync-node3
            String path04 = zookeeper.create("/sync-node3",(df_content+df_incr++).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println(path04);

            //临时有序节点 cmd: create -s -e /sync-node3
            String path05 = zookeeper.create("/sync-node4",(df_content+df_incr++).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            String path06 = zookeeper.create("/sync-node4",(df_content+df_incr++).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(path05);
            System.out.println(path06);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
