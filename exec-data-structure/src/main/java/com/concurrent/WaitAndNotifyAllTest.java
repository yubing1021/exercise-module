package com.concurrent;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-16 21:58
 */
public class WaitAndNotifyAllTest {

    private int i = 0;

    final Object LOCK = new Object();

    private boolean isProduce = false;

    private void produce(){

        synchronized (LOCK){
            while (isProduce){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            i++;
            System.out.println(Thread.currentThread().getName()+" "+i);
            isProduce=true;
        }

    }

    private void consume(){

        synchronized (LOCK){
            while (!isProduce){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" "+i);
            LOCK.notifyAll();
            isProduce=false;
        }
    }

    public static void main(String[] args) {

        WaitAndNotifyAllTest wan = new WaitAndNotifyAllTest();

        for (int i=0; i<4; i++){
            new Thread(()->{
                while (true){
                    wan.produce();
                }
            },"thread-p"+i).start();

            new Thread(()->{
                while (true){
                    wan.consume();
                }
            },"thread-c"+i).start();
        }

       /* new Thread(()->{
            while (true){
                wan.produce();
            }
        },"thread-p").start();

        new Thread(()->{
            while (true){
                wan.consume();
            }
        },"thread-c").start();*/
    }


}
