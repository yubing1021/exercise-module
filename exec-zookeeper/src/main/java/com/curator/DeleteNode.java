package com.curator;

/**
 * @description: 删除节点
 * @author: darben
 * @create: 2020-03-26 16:35
 */
public class DeleteNode extends BaseCuratorServices{

    public static void main(String[] args) {

        DeleteNode obj = new DeleteNode();

        try {
            //简单写法
            //obj.client.delete().forPath("/sync-node9");

            /*
            * guaranteed 直到节点删除成功
            * deletingChildrenIfNeeded 删除当前节点所有子节点，再删除本身
            * withVersion 版本校验
            * */

            obj.client.delete().guaranteed().deletingChildrenIfNeeded().withVersion(-1).forPath("/sync-node9");

            Thread.sleep(Integer.MAX_VALUE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
