package com.datast;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @description: 二叉查找树
 * @author: darben
 * @create: 2020-04-01 20:59
 */
public class BinarySearchTree {

    private class Node {
        //数据内容
        int data;
        //左节点
        Node left;
        //右节点
        Node right;
    }

    //根节点
    private Node root;

    //创建树
    public void insert(int key) {
        Node p = new Node(); //待插入的节点
        p.data = key;

        if (root == null) {
            root = p;
        } else {
            Node parent = new Node();
            Node current = root;
            while (true) {
                parent = current;
                if (key > current.data) {
                    current = current.right; // 右子树
                    if (current == null) {
                        parent.right = p;
                        return;
                    }
                }
                else //本程序没有做key出现相等情况的处理，暂且假设用户插入的节点值都不同
                {
                    current = current.left; // 左子树
                    if (current == null) {
                        parent.left = p;
                        return;
                    }
                }
            }
        }
    }

    // 前序遍历,"中左右"
    public void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrder(root.left);
        preOrder(root.right);
    }
    }

    // 右序遍历,"中右左"
    public void rightOrder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrder(root.right);
            preOrder(root.left);
        }
    }

    static class StopException extends RuntimeException {
    }

    public static volatile int tempSum =0;
    public static StringBuffer sbf = new StringBuffer("");
    public static boolean searchFlag = false;

    //通过前序遍历进行查找
    public void searchNodeByPreOrder(Node root, int targetSum) throws Exception {
        if(root != null){
            BinarySearchTree.sbf.append(root.data + ",");
            BinarySearchTree.tempSum += root.data;
            if(targetSum==BinarySearchTree.tempSum){
                System.out.println(sbf.toString().substring(0,sbf.length()-1));
                BinarySearchTree.searchFlag=true;
                throw new StopException();
            }
            else if(targetSum>BinarySearchTree.tempSum){
                searchNodeByPreOrder(root.left, targetSum);
                searchNodeByPreOrder(root.right, targetSum);
            }
            else{
                throw new StopException();
            }
        }
    }

    //通过右序遍历进行查找
    public void searchNodeByRightOrder(Node root, int targetSum) throws Exception {
        if(root != null){
            BinarySearchTree.sbf.append(root.data + ",");
            BinarySearchTree.tempSum += root.data;
            if(targetSum==BinarySearchTree.tempSum){
                System.out.println(sbf.toString().substring(0,sbf.length()-1));
                BinarySearchTree.searchFlag=true;
                throw new StopException();
            }
            else if(targetSum>BinarySearchTree.tempSum){
                searchNodeByRightOrder(root.right, targetSum);
                searchNodeByRightOrder(root.left, targetSum);
            }
            else{
                throw new StopException();
            }
        }
    }

    //测试
    public static void main(String[] args)   {
        BinarySearchTree tree = new BinarySearchTree();

        //控制台输入
        Scanner scanner = new Scanner(System.in);
        int index = 1;
        System.out.println("请输入【一行字符串，多个正整数，之间用,隔开】：");
        while (true){
            String line = scanner.nextLine();
            if(index ==1 ){
                if(line.contains(",")){
                    String[] datas = line.split(",");
                    Arrays.stream(datas).flatMapToInt(s -> IntStream.of(Integer.parseInt(s))).forEach(tree::insert);
                    //tree.preOrder(tree.root);
                    //tree.rightOrder(tree.root);
                    index++;
                }
                else{
                    System.out.println("请按照规范输入:【一行字符串，多个正整数，之间用,隔开】,否则生成不了二叉排序树");
                }
            }
            else{
                Integer targetSum = Integer.parseInt(line.trim());
                BinarySearchTree.searchFlag=false;

                try {
                    BinarySearchTree.sbf=new StringBuffer("");
                    BinarySearchTree.tempSum = 0;
                    tree.searchNodeByPreOrder(tree.root, targetSum);
                }
                catch (Exception e){
                    //忽略异常
                }

                try {
                    BinarySearchTree.sbf=new StringBuffer("");
                    BinarySearchTree.tempSum = 0;
                    tree.searchNodeByRightOrder(tree.root, targetSum);
                }
                catch (Exception e){
                    //忽略异常
                }

                if(!BinarySearchTree.searchFlag){
                    System.out.println("error");
                }
            }
        }
    }
}
