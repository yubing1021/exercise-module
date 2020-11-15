package com.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: TT
 * @author: darben
 * @create: 2020-08-22 19:15
 */
public class TestDemo {

    public static String str = "";

    public static void main(String[] args) {
        /*List<Integer> list =new ArrayList<>();

        list.addAll(Arrays.asList(5,3,1));
        list.add(6);
        list.add(0,4);
        list.add(0,8);
        list.remove(1);

        System.out.println(list);*/

        /*List<Integer> list = new ArrayList<>(20);

        String str="";
        System.out.print(str.split(",").length);

        Double oD = 3d;*/

        /*int i=5;
        int j=10;
        System.out.println(~j);
        System.out.println(i+~j);*/

        /*System.out.println(str);

        String s1=new String("xyz" );
        String s2=new String("xyz" );
        Boolean b1=s1.equals(s2);
        Boolean b2=(s1==s2);
        System .out.print(b1+ " " +b2);*/

        /*System.out.println(14^3);
        Enclosingone enclosingone = new Enclosingone();
        Enclosingone.InsideOne insideOne = enclosingone.new InsideOne();
        System.out.println(insideOne.getA());*/

        System.out.println(100%3);
        System.out.println(100%3.0);
        try {
            // Thread.sleep(1);
            TestDemo testDemo = new TestDemo();
            testDemo.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
