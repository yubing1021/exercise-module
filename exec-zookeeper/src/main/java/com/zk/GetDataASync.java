package com.zk;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

/**
 * @description: 查询节点数据->异步
 * @author: darben
 * @create: 2020-03-24 16:54
 */
public class GetDataASync extends ZkBaseServices{

    public static void main(String[] args) throws InterruptedException {

        GetDataASync obj = new GetDataASync();

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void handler() {

        zookeeper.getData("/sync-node1", true, new IDataCallback(), "上下文" );

    }

    static class IDataCallback implements AsyncCallback.DataCallback{

        @Override
        public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
            System.out.println(new String(bytes));
            System.out.println("stat:"+stat);
        }

    }

}
