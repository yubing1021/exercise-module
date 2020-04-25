package com.zk;

import org.apache.zookeeper.AsyncCallback;

/**
 * @description: 删除节点->同步
 * @author: darben
 * @create: 2020-03-24 17:25
 */
public class DeleteNodeASync extends ZkBaseServices{

    public static void main(String[] args) throws InterruptedException {
        DeleteNodeASync obj = new DeleteNodeASync();

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void handler() {

        zookeeper.delete("/sync-node7", -1,new IVoidCallback(),"上下文");
    }

    static class IVoidCallback implements AsyncCallback.VoidCallback{

        @Override
        public void processResult(int i, String s, Object o) {
            StringBuilder sb = new StringBuilder();
            sb.append("i="+i).append("\t");
            sb.append("s"+s).append("\t");
            sb.append("o="+o).append("\t");
            System.out.println(sb.toString());
        }
    }
}

