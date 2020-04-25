package com.other;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-21 13:59
 */
public class TestChar {

    public static void main(String[] args) {

        //将*****A*B*C调整为*******ABC

        String temp = "*****A*B*C";

        char[] chars = temp.toCharArray();
        System.out.println(chars);

        StringBuilder target = new StringBuilder();
        for (char c:chars){
            if('*'==c){
                target.append(c);
            }
        }
        System.out.println(target.toString());
        target.append(temp.replaceAll("\\*",""));
        System.out.println(target.toString());
    }

}
