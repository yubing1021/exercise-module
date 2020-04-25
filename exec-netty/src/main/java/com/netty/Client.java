package com.netty;

import com.handler.HiHandler;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: Netty客户端
 * @author: darben
 * @create: 2020-03-22 15:30
 */
public class Client {

    public static void main(String[] args) {

        //客户端
        ClientBootstrap bootstrap = new ClientBootstrap();

        //线程池
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        //socket工厂
        bootstrap.setFactory(new NioClientSocketChannelFactory(boss,worker));
        //管道工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder",new StringDecoder());
                pipeline.addLast("encoder",new StringEncoder());
                pipeline.addLast("hiHandler",new HiHandler());
                return pipeline;
            }
        });

        //连接服务端
        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("localhost", 10001));
        Channel channel = channelFuture.getChannel();

        System.out.println("客户端连接成功...");
        System.out.println("======================================================");

        Scanner scanner = new Scanner(System.in);
        while (true){
            channel.write(scanner.nextLine());
        }
    }


}
