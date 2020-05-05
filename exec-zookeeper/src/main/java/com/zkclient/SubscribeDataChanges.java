package com.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;
import org.I0Itec.zkclient.serialize.ZkSerializer;

/**
 * @description:订阅节点内容发生变化
 * @author: darben
 * @create: 2020-03-27 11:57
 */
public class SubscribeDataChanges extends BaseZkClientServices {


    public static void main(String[] args) throws InterruptedException {

        SubscribeDataChanges obj =new SubscribeDataChanges();
        obj.client.subscribeDataChanges("/sync-node8", new ZkNodeDateChanges());

        Thread.sleep(Integer.MAX_VALUE);
    }

    public SubscribeDataChanges(){
        this.client = new ZkClient(ADDRESS,5000,5000, new BytesPushThroughSerializer());
    }

    static class ZkNodeDateChanges implements IZkDataListener {

        @Override
        public void handleDataChange(String s, Object o) throws Exception {
            System.out.println("datachange event");
            System.out.println(s);
            System.out.println(o);
        }

        @Override
        public void handleDataDeleted(String s) throws Exception {
            System.out.println("datedelte event");
            System.out.println(s);
        }
    }

}
