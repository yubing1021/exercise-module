package com.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

/**
 * @description: zk端连接
 * @author: darben
 * @create: 2020-03-24 12:39
 */
public abstract class ZkBaseServices implements Watcher {

    //zk cluster 连接地址信息
    public String address ="192.168.8.196:12181,192.168.8.196:12182,192.168.8.196:12183";

    //zookeeper客户端
    public ZooKeeper zookeeper;

    /**
    *@Description: 连接成功之后回调的方法，并进行后续的业务处理
    *@Param:
    *@return:
    *@date: 2020/3/24
    */
    @Override
    public void process(WatchedEvent watchedEvent) {

        System.out.println("收到连接事件:\t"+watchedEvent);

        try {
            //三部分信息，状态、事件类型、当前触发这个事件相关联的节点path
            if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
                //首次连接成功，避免断口重连情况
                if(watchedEvent.getType() == Event.EventType.None && null == watchedEvent.getPath()){

                    System.out.println("handler");
                    System.out.println("==============================================================");
                    handler();
                }
                else{
                    //子节点发生变化事件
                    if(watchedEvent.getType() == Event.EventType.NodeChildrenChanged){
                        System.out.println("NodeChildrenChanged");
                        //再次获取子节点列表
                        //watchedEvent.getPath() ->哪个节点发生变化
                        List<String> children = zookeeper.getChildren(watchedEvent.getPath(), true);
                        System.out.println(children);
                    }
                    //节点数据发生改变
                    else if(watchedEvent.getType() == Event.EventType.NodeDataChanged){
                        System.out.println("NodeDataChanged");
                        byte[] data = zookeeper.getData(watchedEvent.getPath(), true, new Stat());
                        System.out.println(new String(data));
                    }
                    //节点创建、节点删除
                    else if(watchedEvent.getType() == Event.EventType.NodeCreated || watchedEvent.getType() == Event.EventType.NodeDeleted){
                        System.out.println("NodeCreated|NodeDeleted");
                        System.out.println(zookeeper.exists(watchedEvent.getPath(), true));
                    }
                }
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    /**
    *@Description: 构造函数，进行初始工作
    *@Param:
    *@return: 
    *@date: 2020/3/24
    */
    public ZkBaseServices(){
        try {
            //初始化连接信息
            zookeeper = new ZooKeeper(address,5000, this);
            //打印状态
            System.out.println("zookeeper state:\t"+zookeeper.getState());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("连接服务端异常，请检查");
        }
    }

    /**
    *@Description: 业务逻辑抽象
    *@Param:
    *@return:
    *@date: 2020/3/24
    */
    public abstract void handler();

}
