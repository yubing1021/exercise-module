package com.headfirst.decorator.component;

import com.headfirst.decorator.Beverage;

/**
 * @description: 综合咖啡
 * @author: darben
 * @create: 2020-04-21 18:34
 */
public class HouseBlend extends Beverage {

    public HouseBlend(){
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
