package com.headfirst.decorator.achieve;

import com.headfirst.decorator.Beverage;
import com.headfirst.decorator.CondimentDecorator;

/**
 * @description: 装饰者-摩卡
 * @author: darben
 * @create: 2020-04-21 21:10
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public  Mocha(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+", Mocha";
    }

    @Override
    public double cost() {
        return 10.00+beverage.cost();
    }
}
