package com.headfirst.simplefactory;

/**
 * @description: 简单工厂类
 * @author: darben
 * @create: 2020-04-21 21:48
 */
public class SimpleFactory {

    public static IFish getFish(String type){
        IFish fish = null;
        if(type.equals("carp")){
            fish = new Carp();
        }
        else if(type.equals("shark")){
            fish = new Shark();
        }
        else if(type.equals("tuna")){
            fish = new Tuna();
        }
        return fish;
    }

}
