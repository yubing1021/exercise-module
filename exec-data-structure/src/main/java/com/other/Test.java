package com.other;

/**
 * @description:
 * @author: darben
 * @create: 2020-08-22 21:51
 */
public class Test {
    static int x=10;
    static {x+=5;}
    public static void main(String[] args) //4
    {
        int x = -5;
        int y = -12;
        System.out.println(y % x);
    }
    static{x/=3;};
}
