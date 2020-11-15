package com.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-13 21:49
 */
public class TestList {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);

        list.add(11);


        Collections.reverse(list);
        list.stream().forEach(System.out::println);
    }

}
