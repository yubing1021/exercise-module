package com.curator;

import java.util.List;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-26 16:36
 */
public class GetChildren extends BaseCuratorServices{

    public static void main(String[] args) {

        GetChildren obj = new GetChildren();

        try {
            List<String> strings = obj.client.getChildren().forPath("/");
            strings.stream().forEach(System.out::println);

            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
