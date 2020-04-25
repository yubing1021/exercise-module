package com.other;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-05 23:41
 */
public class PrintHeapSize {

    public static void main(String[] args) {
        //-Xmx20m -Xms5m
        //最大堆内存、最小堆内存

        System.out.println("Xmx=");
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024+"M");

        System.out.println("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory()/1024/1024+"M");

        //当前分配的总空间
        System.out.println("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");

        /*
            Xmx=
            20M
            free mem=
            4M
            total mem=
            6M
         */

        //总结：jvm尽可能维持在最小堆

    }
}
