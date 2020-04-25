package com.curator;

import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-26 16:36
 */
public class NodeListener extends BaseCuratorServices {

    public static void main(String[] args) {

        NodeListener obj = new NodeListener();

        try {
            NodeCache nodeCache = new NodeCache(obj.client,"/sync-node8");
            nodeCache.start();

            nodeCache.getListenable().addListener(new NodeCacheListener() {
                //监听数据发生变化
                @Override
                public void nodeChanged() throws Exception {
                    System.out.println(nodeCache.getCurrentData().getPath());
                    byte[] currentData = nodeCache.getCurrentData().getData();
                    System.out.println(new String(currentData));
                }
            });

            Thread.sleep(Integer.MAX_VALUE);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
