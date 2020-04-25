package com.combat.subscribe;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.List;

/**
 * @description:
 * 监控服务器列表变化
 * 监控命令节点，执行命令，来管理config节点
 *
 * @author: darben
 * @create: 2020-03-27 15:42
 */
public class ManageServer {

    private String serverPath;
    private String configPath;
    private String commandPath;
    private ZkClient zkClient;
    private ServerConfig serverConfig;

    private IZkChildListener zkChildListener;
    private IZkDataListener zkDataListener;

    private List<String> workServerList;

    public ManageServer(String serverPath, String commandPath, String configPath, ZkClient zkClient, ServerConfig serverConfig){
        this.serverPath = serverPath;
        this.configPath = configPath;
        this.commandPath = commandPath;
        this.zkClient =zkClient;
        this.serverConfig = serverConfig;

        zkChildListener = new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                workServerList = list;

                System.out.println("work server list changed, new list is:");
                list();
            }
        };

        zkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                String cmdType = new String((byte[])o);
                System.out.println("cmd:"+cmdType);
                execCmd(cmdType);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                //ignore
            }
        };
    }

    public void initRunning(){
        zkClient.subscribeChildChanges(serverPath, zkChildListener);
        zkClient.subscribeDataChanges(commandPath,zkDataListener);
    }

    public void start(){
        initRunning();
    }

    public void stop(){
        zkClient.unsubscribeChildChanges(serverPath, zkChildListener);
        zkClient.unsubscribeDataChanges(commandPath,zkDataListener);
    }

    /**
     *@Description:
     *  控制台中指令的控制
     *  1.list 列出workerserver服务器的列表
     *  2.create 创建config节点
     *  3.modify 修改config节点的内容
     *@Param:
     *@return:
     *@date: 2020/3/27
    */
    public void execCmd(String cmdType){
        if("list".equalsIgnoreCase(cmdType)){
            list();
        }
        else if("create".equalsIgnoreCase(cmdType)){
            create();
        }
        else if("modify".equalsIgnoreCase(cmdType)){
            modify();
        }
        else {
            System.out.println("error command:\t"+cmdType);
        }
    }

    private void list(){
        System.out.println(workServerList.toString());
    }

    private void create(){
        boolean exists = zkClient.exists(configPath);
        if(!exists){
            try {
                //持久节点
                zkClient.createPersistent(configPath, JSON.toJSONString(serverConfig).getBytes());
            }catch (ZkNodeExistsException e){
                zkClient.writeData(configPath, JSON.toJSONString(serverConfig).getBytes());
            }catch (ZkNoNodeException e){
                String parentDir = commandPath.substring(0, configPath.lastIndexOf("/"));
                zkClient.createPersistent(parentDir,true);
                create();
            }
        }
    }

    private void modify(){
        serverConfig.setDbUser(serverConfig.getDbUser()+"_modify");
        try {
            zkClient.writeData(configPath, JSON.toJSONString(serverConfig).getBytes());
        }catch (ZkNoNodeException e){
            create();
        }
    }

}
