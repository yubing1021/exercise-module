package com.headfirst.decorator.achieve;

import com.headfirst.decorator.Beverage;
import com.headfirst.decorator.CondimentDecorator;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-21 21:32
 */
public class Whip extends CondimentDecorator {

    private Beverage beverage;

    public Whip(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+", Whip";
    }

    @Override
    public double cost() {
        return 20+beverage.cost();
    }
}
