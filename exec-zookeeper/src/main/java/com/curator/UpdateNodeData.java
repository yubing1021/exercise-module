package com.curator;

import org.apache.zookeeper.data.Stat;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-26 16:36
 */
public class UpdateNodeData extends BaseCuratorServices {

    public static void main(String[] args) {
        UpdateNodeData obj = new UpdateNodeData();

        Stat stat = new Stat();

        try {
            obj.client.getData().storingStatIn(stat).forPath("/sync-node8");

            Stat stat1 = obj.client.setData().withVersion(stat.getVersion()).forPath("/sync-node8", "0000".getBytes());

            System.out.println(stat1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
