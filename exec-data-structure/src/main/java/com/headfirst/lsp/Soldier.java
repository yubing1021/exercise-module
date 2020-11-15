package com.headfirst.lsp;

/**
 * @description:
 * @author: darben
 * @create: 2020-10-18 15:27
 */
public class Soldier {

    private AbstractGun abstractGun;

    public void setGun(AbstractGun abstractGun) {
        this.abstractGun = abstractGun;
    }

    public void killEnemy() {
        abstractGun.shoot();
    }
}
