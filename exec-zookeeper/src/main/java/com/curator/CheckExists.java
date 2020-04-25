package com.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-27 10:12
 */
public class CheckExists extends BaseCuratorServices {

    public static void main(String[] args) {

        CheckExists obj = new CheckExists();

        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            //异步执行
            obj.client.checkExists().inBackground(new BackgroundCallback() {
                @Override
                public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                    //上下文
                    System.out.println(curatorEvent.getContext());
                    //状态
                    System.out.println(curatorEvent.getStat());
                }
            }, "上下文", executorService).forPath("/sync-node8");

            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
