package com.zkclient;

import com.zkclient.vo.UserInfo;
import org.apache.zookeeper.data.Stat;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-27 11:34
 */
public class GetData extends BaseZkClientServices {

    public static void main(String[] args) {

        GetData obj = new GetData();

        Stat stat =new Stat();
        UserInfo userInfo = obj.client.readData("/sync-node8/001", stat);
        System.out.println(userInfo);
        System.out.println(stat);

    }
}
