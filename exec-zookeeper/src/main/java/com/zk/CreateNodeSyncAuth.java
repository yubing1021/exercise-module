package com.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 创建节点-ACL权限模式
 * @author: darben
 * @create: 2020-03-24 17:32
 */
public class CreateNodeSyncAuth extends ZkBaseServices{

    public static void main(String[] args) throws InterruptedException {

        CreateNodeSyncAuth obj = new CreateNodeSyncAuth();

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void handler() {

        /*
        * 一、权限模式（scheme）：
        * 1、ip (ip白名单)
        * 2、digest （用户名/密码）
        *
        * 二、授权对象（ID）
        * 1、ip模式：具体的IP地址
        * 2、digest模式：uname:Base64(SHA-1(uname:pwd))
        *
        * 三、权限（permission）
        * create(C) delete(D) READ(R) write(W) admin(A)
        *
        * 权限组合 scheme+ID+permission
        *
        * ACL -> Access Controller List
        *
        * */

        //允许ip 192.168.8.196 客户端 读取 节点
        try {
            //cmd: create /sync-node8 ip:127.0.0.1:crwda
            ACL aclIp = new ACL(ZooDefs.Perms.CREATE|ZooDefs.Perms.READ|ZooDefs.Perms.WRITE|ZooDefs.Perms.DELETE|ZooDefs.Perms.ADMIN,
                    new Id("ip","192.168.8.196"));
            //cmd: create /sync-node8 digest:yubing:3XYxqngwuo8fGx36i/5+QTwyeI0=:r
            ACL aclDigest = new ACL(ZooDefs.Perms.READ|ZooDefs.Perms.WRITE, new Id("digest", DigestAuthenticationProvider.generateDigest("yubing:123456")));

            List<ACL> aclList =new ArrayList<>();
            aclList.add(aclIp);
            aclList.add(aclDigest);

            String path = zookeeper.create("/sync-node8", "123456".getBytes(), aclList, CreateMode.PERSISTENT);

            //另外一种方式
            //zookeeper.addAuthInfo("digest","yubing:123456".getBytes());
            //String path = zookeeper.create("/sync-node8", "123456".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            // zkCli客户端查询
            // 访问有权限的节点，使用cmd
            // addauth digest yubing:123456

            System.out.println(path);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
