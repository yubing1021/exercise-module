package com.darben.execdatareids.common;

/**
 * @description: 测试
 * @author: darben
 * @create: 2020-03-13 13:03
 */
public class TestUnitUtils {

    public static void main(String[] args) {
        String one = "true";
        Boolean b1 = Boolean.valueOf(one);

        String one2 = "1";
        Integer i1 = new Integer(one2);
        Integer i2 = 1;
        if(b1){
            System.out.println(i1==i2);
        }
    }

}
