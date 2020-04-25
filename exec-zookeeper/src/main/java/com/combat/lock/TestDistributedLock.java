package com.combat.lock;

import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;

/**
 * @description:测试
 * @author: darben
 * @create: 2020-03-29 14:42
 */
public class TestDistributedLock {

    private static final String  ZOOKEEPER_SERVER = "192.168.43.175:12181,192.168.43.175:12182,192.168.43.175:12183";

    public static void main(String[] args) {

        final ZkClientExt clientExt1 = new ZkClientExt(ZOOKEEPER_SERVER,5000,5000,new BytesPushThroughSerializer());
        final DistributedLock mutex1 = new SimpleDistributedLockMutex(clientExt1,"/Mutex");

        //final ZkClientExt clientExt2 = new ZkClientExt(ZOOKEEPER_SERVER,5000,5000,new BytesPushThroughSerializer());
        //final DistributedLock mutex2 = new SimpleDistributedLockMutex(clientExt2,"/Mutex");

        try {
            mutex1.acquire();
            System.out.println("Client1 locked");

           /* Thread client2Trd = new Thread(()->{
                try {
                    mutex2.acquire();
                    System.out.println("Client2 locked");
                    mutex2.release();
                    System.out.println("Client2 released lock");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            client2Trd.start();
            Thread.sleep(5000);*/

            //mutex1.release();
            //System.out.println("Client1 released lock");

            //client2Trd.join();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
