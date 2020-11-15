package com.headfirst.lsp;

/**
 * @description: 狙击步枪
 * @author: darben
 * @create: 2020-10-18 15:53
 */
public class AUG extends Rifle {

    public void zoomOut() {
        System.out.println("通过望远镜观察敌人");
    }

    public void shoot() {
        System.out.println("AUG射击");
    }
}
