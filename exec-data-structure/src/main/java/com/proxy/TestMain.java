package com.proxy;

import com.proxy.cglib.CglibProxy;
import com.proxy.java.IndexDao;
import com.proxy.java.IndexDaoImpl;
import com.proxy.java.MyInvocationHandler;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-15 17:12
 */
public class TestMain {

    public static void main(String[] args) {

        /*IndexDao indexDao1 = new IndexDaoImpl();
        indexDao1.selectByKey();*/

        //JDK动态代理
        //IndexDao indexDao2 = new MyInvocationHandler(new IndexDaoImpl()).getProxy();
        //indexDao2.selectByKey();

        //CGLib动态代理
        CglibProxy cglibProxy = new CglibProxy();
        IndexDao indexDao3 = (IndexDao) cglibProxy.getCglibProxy(new IndexDaoImpl());
        indexDao3.selectByKey();
    }
}
