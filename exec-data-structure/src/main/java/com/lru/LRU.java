package com.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: LRU算法（最近最久未使用）
 * @author: darben
 * @create: 2020-04-12 09:25
 */
public class LRU<K,V> {

    //当前大小
    private int currentSize;
    //容量
    private int capcicty;
    //所有node节点
    private Map<K,Node> caches;
    //头节点
    private Node first;
    //尾节点
    private Node last;

    public LRU(int size) {
        this.currentSize = 0;
        this.capcicty = size;
        this.caches = new HashMap<>(size);
    }

    /**
    *@Description: 放入元素
    *@Param: 
    *@return: 
    *@date: 2020/4/12
    */
    public void put(K key,V value){
        Node node = caches.get(key);
        if(node == null){
            node = new Node(key,value);
            //判断caches时候已满
            //如果已满，则移除尾端node，将新node添加到头部
            if(caches.size() == capcicty){
                //移除缓存数据
                caches.remove(last);
                //链表处理
                removeLast();
            }
            //链表处理
            moveToHead(node);
        }
        else{
            //如果存在，覆盖value值，则将node移动到头部
            node.value=value;
            //链表处理
            moveToHead(node);
        }

        //缓存数据覆盖
        caches.put(key,node);
    }
    
    /**
    *@Description: 根据key获取元素
    *@Param: 
    *@return: 
    *@date: 2020/4/12
    */
    public Object get(K key){
        Node node =  caches.get(key);
        //如果为空，则返回
        if(node ==null ){
            return null;
        }
        //否则，将node移动到头部
        else{
            moveToHead(node);
            return node;
        }
    }

    /**
    *@Description: 根据key移除元素
    *@Param:
    *@return:
    *@date: 2020/4/12
    */
    public void remove(K key){
        Node node = caches.get(key);
        if(node!=null){
            if(node == last){
                last = node.pre;
            }
            if(node == first){
                first=node.next;
            }
            if(node.pre!=null){
                node.pre.next=node.next;
            }
            if(node.next!=null){
                node.next.pre=node.pre;
            }
        }
        caches.remove(key);
    }

    /**
    *@Description: 清除所有节点
    *@Param:
    *@return:
    *@date: 2020/4/12
    */
    public void clear(){
        caches.clear();
        last=null;
        first=null;
    }

    private void moveToHead(Node node){
        if(first == node){
            return;
        }
        if(first == null || last == null){
            first = last =node;
            return;
        }
        if(node.next!=null){
            node.next.pre = node.pre;
        }
        if(node.pre!=null){
            node.pre.next = node.next;
        }
        if(last == node){
            //存在疑问=
            last = node.pre;
        }

        //疑问
        node.next=first;
        first.pre =node;
        first=node;
        first.pre=null;
    }

    /**
    *@Description: 移除最后一个节点
    *@Param: 
    *@return: 
    *@date: 2020/4/12
    */
    private void removeLast(){
        if(last!=null){
            last=last.pre;
            if(last == null){
                first = null;
            }
            else{
                //存在疑问
                last.next = null;
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node node =first;
        while (node!=null){
            sb.append(String.format("%s:%s ", node.key, node.value));
            node = node.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LRU<Integer,String> lru = new LRU<>(5);
        lru.put(1,"1");
        lru.put(2,"2");
        lru.put(3,"3");
        lru.put(4,"4");
        lru.remove(1);
        lru.remove(3);

        System.out.println(lru.toString());
    }
}
