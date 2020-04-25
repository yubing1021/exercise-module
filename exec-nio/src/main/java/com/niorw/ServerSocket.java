package com.niorw;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

/**
 * @description: Sever
 * @author: darben
 * @create: 2020-03-21 10:30
 */
public class ServerSocket {

    public static void main(String[] args) {
        try {
            //服务器初始化
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            serverSocket.configureBlocking(false);
            serverSocket.bind(new InetSocketAddress("localhost",9999));

            //注册OP_ACCEPT事件（即监听该事件，如果有客户端发来连接请求，则该键在select()后被选中）
            Selector selector =Selector.open();
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);

            Calendar ca = Calendar.getInstance();
            System.out.println("服务端开启了");
            System.out.println("=========================================================");

            //轮询服务
            while (true){
                //选择准备好的事件
                selector.select();

                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    //处理掉后将键移除，避免重复消费(因为下次选择后，还在已选择键集中)
                    iterator.remove();

                    //处理连接请求
                    if(key.isAcceptable()){
                        SocketChannel socket =serverSocket.accept();
                        socket.configureBlocking(false);
                        //注册读事件
                        socket.register(selector,SelectionKey.OP_READ);
                        //keys为所有键，除掉serverSocket注册的键就是已连接socketChannel的数量
                        String message = "连接成功 你是第"+(selector.keys().size()-1)+"个用户";

                        socket.write(ByteBuffer.wrap(message.getBytes()));
                        InetSocketAddress address = (InetSocketAddress) socket.getRemoteAddress();

                        //输出客户端地址
                        System.out.println(ca.getTime()+"\t"+address.getHostString()+":"+address.getPort()+"\t");
                        System.out.println("=========================================================");
                    }

                    if(key.isReadable()){
                        SocketChannel socket = (SocketChannel) key.channel();
                        InetSocketAddress address = (InetSocketAddress) socket.getRemoteAddress();
                        System.out.println(ca.getTime() + "\t" + address.getHostString() +
                                ":" + address.getPort() + "\t");
                        ByteBuffer bf = ByteBuffer.allocate(1024*4);
                        int len =0;
                        byte[] res =new byte[1024*4];
                        //捕获异常
                        try {
                            while ((len = socket.read(bf)) != 0){
                                bf.flip();
                                bf.get(res,0,len);
                                System.out.println(new String(res, 0, len));

                                socket.write(ByteBuffer.wrap(res,0, len));
                                bf.clear();
                            }
                        }
                        catch (IOException e){
                            //客户端关闭了
                            key.cancel();
                            socket.close();
                            System.out.println("客戶端已断开");
                            System.out.println("=========================================================");
                        }
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器异常，即将关闭..........");
            System.out.println("=========================================================");
        }
    }

}
