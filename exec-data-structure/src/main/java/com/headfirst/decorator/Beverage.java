package com.headfirst.decorator;

/**
 * @description: 抽象 饮料基类
 * @author: darben
 * @create: 2020-04-21 17:53
 */
public abstract class Beverage {

    public String description = "Unknown Beverage";

    public String getDescription(){
        return description;
    }

    public abstract double cost();

}
