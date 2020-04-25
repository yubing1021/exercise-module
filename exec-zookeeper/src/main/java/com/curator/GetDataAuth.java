package com.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryUntilElapsed;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-26 16:37
 */
public class GetDataAuth extends BaseCuratorServices {

    public GetDataAuth(){

        RetryPolicy retryPolicy = new RetryUntilElapsed(50000,1000);

        this.client = CuratorFrameworkFactory.builder()
                .connectString(address)
                //添加认证方式
                .authorization("digest", "yubing:123456".getBytes())
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();

        this.client.start();
    }

    public static void main(String[] args) {
        GetDataAuth obj = new GetDataAuth();

        try {
            byte[] bytes = obj.client.getData().forPath("/sync-node9/001");
            System.out.println(new String(bytes));

            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
