package com.lru;

/**
 * @description: 双向链表
 * @author: darben
 * @create: 2020-04-12 09:23
 */
public class Node {

    Object key;
    Object value;
    Node pre;
    Node next;

    public Node(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
}
