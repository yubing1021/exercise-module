package com.handler;

import org.jboss.netty.channel.*;

import java.net.InetSocketAddress;

/**
 * @description: 处理器
 * @author: darben
 * @create: 2020-03-21 21:45
 */
public class HiHandler extends SimpleChannelHandler {

    //重写几个主要方法

    /**
    *@Description: 接收数据逻辑处理
    *@Param:
    *@return: 
    *@date: 2020/3/22
    */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("messageReceived");

        //接收数据处理
        /*ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
        String message = new String(buffer.array());
        System.out.println("接收到的消息："+message);*/

        String message = (String) e.getMessage();
        System.out.println("接收到的消息："+message);

        //回写数据
        /*ChannelBuffer buffer = ChannelBuffers.copiedBuffer("hi".getBytes());
        ctx.getChannel().write(buffer);*/

        //ctx.getChannel().write("hi");

        super.messageReceived(ctx, e);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("exceptionCaught");
        super.exceptionCaught(ctx, e);
    }

    /**
    *@Description: 建立新连接的时候进行触发（使用场景，对连接请求进行过滤，黑白名单，安全处理）
    *@Param: 
    *@return: 
    *@date: 2020/3/22
    */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelConnected");

        Channel channel = ctx.getChannel();
        InetSocketAddress address = (InetSocketAddress) channel.getRemoteAddress();
        System.out.println("服务端连接信息\t"+address.getHostString()+":"+address.getPort());
        System.out.println("请输入：");

        super.channelConnected(ctx, e);
    }

    /**
    *@Description: 连接成功之后，断开连接的时候触发(比如清除连接方面的缓存信息)
    *@Param: 
    *@return: 
    *@date: 2020/3/22
    */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    /**
    *@Description: channel关闭的时候触发
    *@Param:
    *@return:
    *@date: 2020/3/22
    */
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
        super.channelClosed(ctx, e);
    }
}
