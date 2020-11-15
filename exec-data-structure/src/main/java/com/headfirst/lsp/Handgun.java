package com.headfirst.lsp;

/**
 * @description: 手枪
 * @author: darben
 * @create: 2020-10-18 15:29
 */
public class Handgun extends AbstractGun {

    @Override
    void shoot() {
        System.out.println("用手枪射击");
    }
}
