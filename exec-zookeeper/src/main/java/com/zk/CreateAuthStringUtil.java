package com.zk;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-24 17:52
 */
public class CreateAuthStringUtil {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(DigestAuthenticationProvider.generateDigest("yubing:123456"));
    }
}
