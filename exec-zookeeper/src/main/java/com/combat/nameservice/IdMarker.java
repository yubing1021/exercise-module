package com.combat.nameservice;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description: Id生成器
 * @author: darben
 * @create: 2020-03-31 15:20
 */
public class IdMarker {

    //ZkClient客户端
    private ZkClient client = null;
    private final String server;
    //顺序节点的根节点
    private final String root;
    //顺序节点的名称
    private final String nodeName;
    //服务器运行状态
    private volatile boolean running = false;

    private ExecutorService cleanService = null;

    //避免zk-root子节点暴增，浪费资源，创建后提供删除策略
    public enum RemoveMethod{
        NONE,IMMEDIATELY,DELAY
    }

    public IdMarker(String server, String root, String nodeName) {
        this.server = server;
        this.root = root;
        this.nodeName = nodeName;
    }

    /**
    *@Description: 启动服务
    *@Param: 
    *@return: 
    *@date: 2020/3/31
    */
    public void start() throws Exception{

        if(running){
            throw new Exception("server has started ...");
        }
        running =true;
        init();
    }
    
    /**
    *@Description: 停止服务
    *@Param: 
    *@return: 
    *@date: 2020/3/31
    */
    public void stop() throws Exception{
        if(!running){
            throw new Exception("server has stoped ...");
        }
        running =false;
        freeResource();
    }

    /**
    *@Description: 初始化服务器资源
    *@Param:
    *@return:
    *@date: 2020/3/31
    */
    private void init(){
        client = new ZkClient(server, 5000, 5000, new BytesPushThroughSerializer());
        cleanService = Executors.newFixedThreadPool(5);
        try {
            //创建根节点
            client.createPersistent(root,true);
        }catch (ZkNodeExistsException e){
            //ignore
            System.out.println("warn:zknode exists :"+root);
        }
    }

    /**
    *@Description: 释放服务器资源
    *@Param:
    *@return:
    *@date: 2020/3/31
    */
    private void freeResource(){
        //关闭客户端
        if(client!=null){
            client.close();
            client = null;
        }

        //关闭线程池
        cleanService.shutdown();
        try {
            cleanService.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            cleanService = null;
        }
    }

    /**
    *@Description: 检查服务是否运行
    *@Param:
    *@return:
    *@date: 2020/3/31
    */
    private void checkRunning() throws Exception{

        if(!running){
            throw new Exception("请先调用start");
        }
    }

    private String extractId(String str){
        int index = str.lastIndexOf(nodeName);
        if(index>0){
            index = index + nodeName.length();
            return index<=str.length()?str.substring(index):"";
        }
        return str;
    }

    /**
    *@Description: 获取唯一ID
    *@Param:
    *@return:
    *@date: 2020/3/31
    */
    public String generateId(RemoveMethod removeMethod) throws Exception{
        checkRunning();

        final String fullNodePath = root.concat("/").concat(nodeName);

        final String outPath = client.createPersistentSequential(fullNodePath, null);
        System.out.println("outPath:"+outPath);

        if(removeMethod.equals(RemoveMethod.IMMEDIATELY)){
            client.delete(outPath);
        }
        else if(removeMethod.equals(RemoveMethod.DELAY)){
            cleanService.execute(()->{
                client.delete(outPath);
            });
        }

        //node-0000000000,noe-0000000001
        return extractId(outPath);
    }
}
