package com.headfirst.decorator.achieve;

import com.headfirst.decorator.Beverage;
import com.headfirst.decorator.CondimentDecorator;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-21 21:29
 */
public class Milk extends CondimentDecorator {
    private Beverage beverage;

    public Milk(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+", Milk";
    }

    @Override
    public double cost() {
        return 20.0+beverage.cost();
    }
}
