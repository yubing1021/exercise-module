package com.concurrent;

/**
 * @description:
 * 实现依次输出
 * A2B2C3D4E5
 * @author: darben
 * @create: 2020-04-21 13:43
 */
public class WaitAndNotify02 {

    private char[] c1 = new char[]{'A','B','C','D','E'};
    private char[] c2 = new char[]{'1','2','3','4','5'};

    private Object object = new Object();

    private boolean flag = true;

    private void console01(){
        synchronized (object){
            for (char c:c1) {
                try {
                    while (!flag){
                        object.wait();
                    }
                    System.out.print(c);
                    flag = false;
                    object.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void console02(){
        synchronized (object){
            for(char c:c2){
                try {
                    while (flag){
                        object.wait();
                    }
                    System.out.print(c);
                    flag=true;
                    object.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        WaitAndNotify02 waitAndNotify02 = new WaitAndNotify02();
        new Thread(()->{
            waitAndNotify02.console01();
        },"Thread-1").start();
        new Thread(()->{
            waitAndNotify02.console02();
        },"Thread-2").start();
    }

}
