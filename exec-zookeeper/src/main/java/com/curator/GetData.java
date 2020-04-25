package com.curator;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-26 16:35
 */
public class GetData extends BaseCuratorServices {

    public static void main(String[] args) {

        GetData obj = new GetData();

        try {
            byte[] bytes = obj.client.getData().forPath("/sync-node9/001");
            System.out.println(new String(bytes));

            Thread.sleep(Integer.MAX_VALUE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
