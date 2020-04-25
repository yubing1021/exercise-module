package com.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

/**
 * @description: 子节点变化监听
 * @author: darben
 * @create: 2020-03-26 16:37
 */
public class NodeChildrenListener extends BaseCuratorServices {

    public static void main(String[] args) {

        NodeChildrenListener obj = new NodeChildrenListener();

        try {
            PathChildrenCache cache = new PathChildrenCache(obj.client, "/sync-node8",true);
            cache.start();
            cache.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {

                    switch (event.getType()){
                        case CHILD_ADDED:
                            System.out.println("CHILD_ADDED");
                            break;
                        case CHILD_UPDATED:
                            System.out.println("CHILD_UPDATED");
                            break;
                        case CHILD_REMOVED:
                            System.out.println("CHILD_REMOVED");
                            break;
                        default:
                            System.out.println(event.getType());
                            break;
                    }
                }
            });

            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
