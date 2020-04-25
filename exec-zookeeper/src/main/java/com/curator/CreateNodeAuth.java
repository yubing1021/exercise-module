package com.curator;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-26 16:37
 */
public class CreateNodeAuth extends BaseCuratorServices{

    public static void main(String[] args) {

        CreateNode obj = new CreateNode();

        try {
            ACL aclDigest = new ACL(ZooDefs.Perms.ALL,new Id("digest", DigestAuthenticationProvider.generateDigest("yubing:123456")));
            List<ACL> acls = new ArrayList<>();
            acls.add(aclDigest);

            String path = obj.client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .withACL(acls)
                    .forPath("/sync-node9/001","8989".getBytes());

            System.out.println(path);

            Thread.sleep(Integer.MAX_VALUE);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
