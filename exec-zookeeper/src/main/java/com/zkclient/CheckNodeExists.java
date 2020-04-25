package com.zkclient;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-27 11:45
 */
public class CheckNodeExists extends BaseZkClientServices {

    public static void main(String[] args) {

        CheckNodeExists obj = new CheckNodeExists();

        boolean exists = obj.client.exists("/sync-node8");
        System.out.println(exists);

    }

}
