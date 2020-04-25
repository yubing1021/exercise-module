package com.headfirst.decorator.component;

import com.headfirst.decorator.Beverage;

/**
 * @description: 组件 - 脱脂咖啡
 * @author: darben
 * @create: 2020-04-21 21:19
 */
public class Decaf extends Beverage {

    public Decaf(){
        description="Decaf";
    }

    @Override
    public double cost() {
        return 2.0;
    }
}
