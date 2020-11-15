package com.headfirst.lsp;

/**
 * @description: 阻击手
 * @author: darben
 * @create: 2020-10-18 15:54
 */
public class Snipper {

    private AUG aug;

    public void setRifle(AUG aug) {
        this.aug = aug;
    }

    public void killEnemy() {
        aug.shoot();
    }
}
