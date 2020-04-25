package com.headfirst.decorator;

/**
 * @description: 调料-抽象类
 * 所有调料装饰者必须重新实现getDescription方法，
 * @author: darben
 * @create: 2020-04-21 18:20
 */
public abstract class CondimentDecorator extends Beverage{
    public abstract String getDescription();
}
