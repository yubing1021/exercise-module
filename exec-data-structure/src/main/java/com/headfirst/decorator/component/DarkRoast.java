package com.headfirst.decorator.component;

import com.headfirst.decorator.Beverage;

/**
 * @description: 组件 - 深培咖啡
 * @author: darben
 * @create: 2020-04-21 21:16
 */
public class DarkRoast extends Beverage {

    public DarkRoast(){
        description = "DarkRoast";
    }

    @Override
    public double cost() {
        return 100.0;
    }
}
