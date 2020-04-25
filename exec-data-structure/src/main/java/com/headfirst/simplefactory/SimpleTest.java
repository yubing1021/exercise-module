package com.headfirst.simplefactory;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-21 21:56
 */
public class SimpleTest {

    public static void main(String[] args) {
        IFish fish = SimpleFactory.getFish("carp");
        fish.description();
    }

}
