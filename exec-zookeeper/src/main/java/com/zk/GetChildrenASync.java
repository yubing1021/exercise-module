package com.zk;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @description: 获取子节点集合->异步方式
 * @author: darben
 * @create: 2020-03-24 16:30
 */
public class GetChildrenASync extends ZkBaseServices{

    public static void main(String[] args) throws InterruptedException {

        GetChildrenASync obj = new GetChildrenASync();

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void handler() {
        zookeeper.getChildren("/",true,new IChildrenCallback(), "上下文");
    }

    static class IChildrenCallback implements AsyncCallback.Children2Callback{

        @Override
        public void processResult(int i, String s, Object o, List<String> list, Stat stat) {
            StringBuilder sb = new StringBuilder();
            sb.append("i="+i).append("\t");
            sb.append("s="+s).append("\t");
            sb.append("obj="+o).append("\t");
            sb.append("list="+list).append("\t");
            sb.append("stat="+stat);
            System.out.println(sb.toString());

        }
    }

}
