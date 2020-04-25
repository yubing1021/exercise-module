package com.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.zookeeper.CreateMode;

/**
 * @description: 创建节点
 * @author: darben
 * @create: 2020-03-26 16:16
 */
public class CreateNode extends BaseCuratorServices{

    public static void main(String[] args) {

        CreateNode obj = new CreateNode();

        try {
            String path =obj.client
                        .create()
                        //表示父节点不存在，先创建父节点，再创建子节点
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .forPath("/sync-node9/001", "123".getBytes());

            String path2 =obj.client
                    .create()
                    //表示父节点不存在，先创建父节点，再创建子节点
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath("/sync-node9/002", "8989899".getBytes());

            System.out.println(path);
            System.out.println(path2);

            /*
            * 说明：上述创建的临时节点为/sync-node9/001，如果父节点/sync-node9不存在，则会被创建一个永久的/sync-node9节点
            * */

            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
