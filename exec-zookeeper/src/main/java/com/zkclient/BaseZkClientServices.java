package com.zkclient;

import com.zkclient.vo.UserInfo;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.I0Itec.zkclient.serialize.ZkSerializer;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-27 11:14
 */
public class BaseZkClientServices {

    public static final String ADDRESS="192.168.8.196:12181,192.168.8.196:12182,192.168.8.196:12183";

    public ZkClient client;

    private UserInfo userInfo;

    public BaseZkClientServices(){
        client=new ZkClient(ADDRESS,5000,5000, new SerializableSerializer());
        System.out.println("connecter ok!");
    }

    public UserInfo getUserInfo() {
        userInfo = new UserInfo();
        userInfo.setId(1001);
        userInfo.setName("余兵");
        userInfo.setAge(20);
        return userInfo;
    }
}
