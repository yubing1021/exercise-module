package com.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 排序
 * @author: darben
 * @create: 2020-03-29 12:22
 */
public class Sort {

    public static void main(String[] args) {

        List<String> temp = new ArrayList<>();
        temp.add("temnode0003");
        temp.add("temnode0001");
        temp.add("temnode0002");
        temp.add("temnode0004");

        List<String> strings = temp.stream().sorted().collect(Collectors.toList());
        System.out.println(strings);

        System.out.println(strings.indexOf("0002"));
    }

}
