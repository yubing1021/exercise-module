package com.zk;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

/**
 * @description: 创建节点->异步方式
 * @author: darben
 * @create: 2020-03-24 15:33
 */
public class CreateNodeASync extends ZkBaseServices{

    public static void main(String[] args) throws InterruptedException {
        CreateNodeASync obj =new CreateNodeASync();

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void handler() {
        //异步创建永久节点
        zookeeper.create("/async-node1","123456".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT,new IStringCallback(),"上下文1");

       zookeeper.create("/async-node2","78910".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT_SEQUENTIAL,new IStringCallback(),"上下文2");

        zookeeper.create("/async-node2","78910".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT_SEQUENTIAL,new IStringCallback(),"上下文3");
    }

    static class IStringCallback implements AsyncCallback.StringCallback{

        @Override
        public void processResult(int i, String s, Object o, String s1) {
            //参数说明
            /*
             * i 返回码 0 处理成功 -110已存在该节点
             * s 创建节点路径
             * o 上下文信息
             * s1 zk节点真实路径
             */
            StringBuilder sb = new StringBuilder();
            sb.append("i="+i).append("\t");
            sb.append("s="+s).append("\t");
            sb.append("obj="+o).append("\t");
            sb.append("s1="+s1);
            System.out.println(sb.toString());

            /*
             *   i=-110	s=/async-node1	obj=上下文1	s1=null
             *   i=0	s=/async-node2	obj=上下文2	s1=/async-node20000000044
             *   i=0	s=/async-node2	obj=上下文3	s1=/async-node20000000045
             */
        }
    }

}
