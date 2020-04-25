package com.zkclient;

import com.zkclient.vo.UserInfo;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-27 11:49
 */
public class UpdateNode extends BaseZkClientServices {

    public static void main(String[] args) throws InterruptedException {

        UpdateNode obj = new UpdateNode();

        UserInfo u = obj.getUserInfo();
        u.setId(1004);
        u.setName("张三");
        u.setAge(100);
        obj.client.writeData("/sync-node8/002",u);

        UserInfo u2 = obj.client.readData("/sync-node8/002");
        System.out.println(u2);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
