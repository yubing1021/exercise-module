package com.zk;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

/**
 * @description:判断节点是否存在->异步方式
 * @author: darben
 * @create: 2020-03-24 21:50
 */
public class NodeExistsASync extends ZkBaseServices {

    public static void main(String[] args) throws InterruptedException {

        NodeExistsASync obj = new NodeExistsASync();

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void handler() {

        zookeeper.exists("/sync-node8",true,new IStatCallback(),"上下文");

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
