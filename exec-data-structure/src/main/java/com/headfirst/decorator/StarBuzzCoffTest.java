package com.headfirst.decorator;

import com.headfirst.decorator.achieve.Milk;
import com.headfirst.decorator.achieve.Mocha;
import com.headfirst.decorator.component.DarkRoast;
import com.headfirst.decorator.component.Espresso;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-21 21:13
 */
public class StarBuzzCoffTest {

    public static void main(String[] args) {

        /*Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription()+" $"+beverage.cost());*/

        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Milk(beverage2);
        System.out.println(beverage2.getDescription()+" $"+beverage2.cost());

    }

}
