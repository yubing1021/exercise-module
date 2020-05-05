package com.datast;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-06 18:32
 */
public class TestMap {

    public static void main(String[] args) {

        //Hashtable table = new Hashtable();
        /*table.contains(1);
        table.containsKey(2);
        table.containsValue(3);
        table.put(null,1);*/


        HashMap map = new HashMap();
        map.containsKey(1);
        map.containsValue(2);
        map.put(null,null);

        //Map
        //HashMap、HashTable、TreeMap、ConcurrentHashMap

        //List
        //ArrayList、LinkList、Vector、Stack

        //Set
        //HashSet、TreeSet、LinkedHashSet

        HashSet set = new HashSet();
        set.add(1);

        //BlockingQueue queue = new LinkedBlockingDeque();


        Vector<Integer> vectors = new Vector<>();
        vectors.add(1);
        vectors.add(2);
        vectors.add(3);
        vectors.add(4);

        vectors.stream().forEach(System.out::println);


        TreeMap<Integer,String> treeMap = new TreeMap<>();
        treeMap.put(1,"2");

        Map<String,Object> retMap = new HashMap<>();
        Map<String,Object> synchronizedMap = Collections.synchronizedMap(retMap);
        synchronizedMap.put("1",2);
        synchronizedMap.get("1");


    }
}
