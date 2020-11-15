package com.headfirst.lsp;

/**
 * @description:步枪
 * @author: darben
 * @create: 2020-10-18 15:29
 */
public class Rifle extends AbstractGun {

    @Override
    void shoot() {
        System.out.println("用步枪射击");
    }
}
