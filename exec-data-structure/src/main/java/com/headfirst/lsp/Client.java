package com.headfirst.lsp;

/**
 * @description:
 * @author: darben
 * @create: 2020-10-18 15:27
 */
public class Client {

    public static void main(String[] args) {
        Soldier soldier = new Soldier();
        soldier.setGun(new Handgun());
        soldier.killEnemy();

        soldier.setGun(new MachineGun());
        soldier.killEnemy();

        Snipper snipper = new Snipper();
        snipper.setRifle((AUG) new Rifle());
        snipper.killEnemy();
    }
}
