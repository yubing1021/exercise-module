package com.other;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-22 09:30
 */
public class TestIncr {

    public static void main(String[] args) {
        int i=0;
        int j = i++;
        System.out.println(i+";"+j);

        Integer m = new Integer(100);
        Integer n = new Integer(100);
        int p = 100;
        Long q =100l;
        System.out.println(m==n);
        System.out.println(m.equals(n));
        System.out.println(m==p);
        System.out.println(m==q.intValue());
    }

}
