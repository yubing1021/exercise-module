package com.headfirst.simplefactory;

/**
 * @description: 金枪鱼
 * @author: darben
 * @create: 2020-04-21 21:51
 */
public class Shark implements IFish {
    @Override
    public void description() {
        System.out.println("鲨鱼");
    }
}
