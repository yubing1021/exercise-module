package com.headfirst.decorator.achieve;

import com.headfirst.decorator.Beverage;
import com.headfirst.decorator.CondimentDecorator;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-21 21:30
 */
public class Soy extends CondimentDecorator {

    private Beverage beverage;

    public Soy(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+", Soy";
    }

    @Override
    public double cost() {
        return 5.0+beverage.cost();
    }
}
