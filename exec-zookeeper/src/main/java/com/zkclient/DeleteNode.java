package com.zkclient;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-27 11:47
 */
public class DeleteNode extends BaseZkClientServices{

    public static void main(String[] args) {

        DeleteNode obj = new DeleteNode();

        //循环删除，如果含有子节点
        boolean delete = obj.client.deleteRecursive("/sync-node8");

        //无子节点删除方法
        boolean b = obj.client.delete("/sync-node8/001");

    }

}
