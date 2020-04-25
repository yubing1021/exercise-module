package com.zk;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

/**
 * @description: 修改节点->异步方式
 * @author: darben
 * @create: 2020-03-24 16:06
 */
public class UpdateNodeASync extends ZkBaseServices{

    public static void main(String[] args) throws InterruptedException {

        UpdateNodeASync obj =new UpdateNodeASync();

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void handler() {

        //异步更新节点数据
        zookeeper.setData("/sync-node1", "0011".getBytes(), -1, new IStatCallback(), "上下文");
    }

    static class IStatCallback implements AsyncCallback.StatCallback{

        @Override
        public void processResult(int i, String s, Object o, Stat stat) {
            StringBuilder sb = new StringBuilder();
            sb.append("i="+i).append("\t");
            sb.append("s="+s).append("\t");
            sb.append("obj="+o).append("\t");
            sb.append("stat="+stat);
            System.out.println(sb.toString());
        }
    }

}
