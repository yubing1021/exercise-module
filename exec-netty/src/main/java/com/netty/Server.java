package com.netty;

import com.handler.HelloHandler;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: Netty服务类
 * @author: darben
 * @create: 2020-03-21 21:36
 */
public class Server {

    public static void main(String[] args) {

        //Netty服务类
        ServerBootstrap bootstrap = new ServerBootstrap();

        //线程池
        //负责监听端口的
        ExecutorService boss = Executors.newCachedThreadPool();
        //负责读写任务
        ExecutorService worker = Executors.newCachedThreadPool();

        //设置nio socket 工厂
        bootstrap.setFactory(new NioServerSocketChannelFactory(boss,worker));

        //设置管道工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                //创建管道，装载一大堆过滤器
                ChannelPipeline pipeline = Channels.pipeline();
                //对上行数据进行处理
                pipeline.addLast("decode",new StringDecoder());
                //对下行数据进行处理
                pipeline.addLast("encode",new StringEncoder());
                //设置Handler
                pipeline.addLast("helloHandler",new HelloHandler());
                return pipeline;
            }
        });

        //绑定监听端口
        bootstrap.bind(new InetSocketAddress("localhost",10001));

        System.out.println("服务端启动成功，开始监听连接...");
        System.out.println("======================================================");
    }

}
