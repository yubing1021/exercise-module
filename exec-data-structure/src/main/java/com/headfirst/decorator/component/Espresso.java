package com.headfirst.decorator.component;

import com.headfirst.decorator.Beverage;

/**
 * @description: 浓缩咖啡
 * @author: darben
 * @create: 2020-04-21 18:23
 */
public class Espresso extends Beverage {

    public Espresso(){
        description="Espresso";
    }

    @Override
    public double cost() {
        return 50.0;
    }
}
