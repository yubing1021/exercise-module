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
 * @create: 2020-03-27 11:34
 */
public class GetDataAuth extends BaseZkClientServices{

    public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException {

        GetDataAuth obj = new GetDataAuth();

        //method3 with defined acl
        /*ACL aclDigest = new ACL(ZooDefs.Perms.READ|ZooDefs.Perms.WRITE, new Id("digest", DigestAuthenticationProvider.generateDigest("yubing:123456")));
        List<ACL> aclList =new ArrayList<>();
        aclList.add(aclDigest);
        UserInfo userInfo = obj.getUserInfo();
        userInfo.setId(1003);
        String path3 = obj.client.create("/sync-node8/004", userInfo, aclList, CreateMode.PERSISTENT);
        System.out.println(path3);*/

        //添加权限
        obj.client.addAuthInfo("digest","yubing:123456".getBytes());
        Stat stat = new Stat();
        UserInfo u = obj.client.readData("/sync-node8/004",stat);
        System.out.println(u);
        System.out.println(stat);

        Thread.sleep(Integer.MAX_VALUE);
    }

}
