package com.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-27 10:25
 */
public class CreateSession {

    public static String address="192.168.8.196:12181,192.168.8.196:12182,192.168.8.196:12183";

    public static void main(String[] args) {

        ZkClient client = new ZkClient(address,5000,5000, new SerializableSerializer());

        System.out.println("connected ok");

    }
}
