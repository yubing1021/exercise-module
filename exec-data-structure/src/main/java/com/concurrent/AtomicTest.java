package com.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-16 10:15
 */
public class AtomicTest {

    public static void main(String[] args) {

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        atomicBoolean.set(true);
        System.out.println(atomicBoolean.get());

        AtomicInteger atomicInteger = new AtomicInteger(20);
        atomicInteger.getAndSet(1);
        atomicInteger.getAndAdd(-1);
        System.out.println(atomicInteger.get());

    }

}
