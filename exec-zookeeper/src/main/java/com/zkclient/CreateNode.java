package com.zkclient;

import com.zkclient.vo.UserInfo;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-27 11:14
 */
public class CreateNode extends BaseZkClientServices{

    public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException {

        CreateNode obj = new CreateNode();

        //method1
        UserInfo userInfo =obj.getUserInfo();
        String path = obj.client.create("/sync-node8/002", userInfo, CreateMode.PERSISTENT);
        System.out.println(path);
        Stat stat =new Stat();
        UserInfo u = obj.client.readData("/sync-node8/002",stat);
        System.out.println(u);

        //method2 with acl
        userInfo.setId(1002);
        String path2 = obj.client.create("/sync-node8/003", userInfo, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path2);
        u = obj.client.readData("/sync-node8/003",stat);
        System.out.println(u);

        Thread.sleep(Integer.MAX_VALUE);
    }

}
