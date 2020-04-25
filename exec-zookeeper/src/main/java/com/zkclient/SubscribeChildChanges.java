package com.zkclient;

import org.I0Itec.zkclient.IZkChildListener;

import java.util.List;

/**
 * @description: 事件订阅：节点的子节点列表发生变化事件
 * @author: darben
 * @create: 2020-03-27 11:53
 */
public class SubscribeChildChanges extends BaseZkClientServices {

    public static void main(String[] args) throws InterruptedException {

        SubscribeChildChanges obj = new SubscribeChildChanges();

        obj.client.subscribeChildChanges("/sync-node8",new ZkClildrenListener());

        Thread.sleep(Integer.MAX_VALUE);
    }

    static class ZkClildrenListener implements IZkChildListener{

        @Override
        public void handleChildChange(String s, List<String> list) throws Exception {
            //节点的子节点列表的变化
            //节点本身被删除的时候
            //节点本身被创建的时候
            System.out.println(s);
            System.out.println(list);
        }
    }

}
