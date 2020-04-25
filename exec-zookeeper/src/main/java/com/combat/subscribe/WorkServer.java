package com.combat.subscribe;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;

/**
 * @description: 工作服务器
 * 1.监听config节点的变化，更新自己的配置信息
 * 2.注册到server节点上
 *
 * @author: darben
 * @create: 2020-03-27 14:48
 */
public class WorkServer {

    private ZkClient zkClient;
    private String configPath;
    private String serverPath;
    private ServerData serverData;
    private ServerConfig serverConfig;
    private IZkDataListener dataListener;

    /**
     *@Description:
     * configPath 配置节点的路径
     * serverpath 工作服务节点的路径
     * serverData 当前服务器的信息
     *@return:
     *@date: 2020/3/27
     */
    public WorkServer(String configPath, String serverPath, ServerData serverData, ZkClient zkClient, ServerConfig initConfig){
        this.zkClient = zkClient;
        this.configPath = configPath;
        this.serverPath =serverPath;
        this.serverConfig = initConfig;
        this.serverData = serverData;

        //用于监听config节点的变化
        this.dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                String retJson =  new String((byte[])o);
                ServerConfig serverConfigLocal = JSON.parseObject(retJson, ServerConfig.class);
                updateConfig(serverConfigLocal);

                System.out.println("New work server config is:\t"+retJson);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {

            }
        };
    }

    public void start(){
        System.out.println("work server start ....");
        initRunning();
    }

    public void stop(){
        //取消订阅
        this.zkClient.unsubscribeDataChanges(configPath,dataListener);
    }

    public void initRunning(){

        registMe();

        //监听config节点的数据变化
        zkClient.subscribeDataChanges(configPath,dataListener);

    }

    private void registMe(){
        try {
            //创建server自己的临时节点
            String mePath = serverPath.concat("/").concat(serverData.getAddress());
            zkClient.createEphemeral(mePath, JSON.toJSONString(serverData).getBytes());
        }catch (ZkNoNodeException e){
            //创建父节点
            zkClient.createPersistent(serverPath,true);
            //再次创建
            registMe();
        }
    }

    private void updateConfig(ServerConfig serverConfig){
        this.serverConfig = serverConfig;
    }

}
