package com.zkclient;

import java.util.List;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-27 11:43
 */
public class GetChildren extends BaseZkClientServices{

    public static void main(String[] args) throws InterruptedException {

        GetChildren obj = new GetChildren();

        List<String> children = obj.client.getChildren("/sync-node10");
        children.stream().forEach(System.out::println);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
