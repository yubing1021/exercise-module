package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @description: NIO Client 客户端
 * NIO-与选择器进行交互
 * @author: darben
 * @create: 2020-03-20 17:28
 */
public class NIOClient2 {

    //缓冲区大小
    private static int blockSize = 4096;

    //发送和接收数据缓冲区
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
    private static ByteBuffer receiveBuffer = ByteBuffer.allocate(blockSize);

    //Socket服务端连接信息
    private final static InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1",8091);

    private static int flag =0;

    public static void main(String[] args) throws IOException {
        //1.连接注册操作
        SocketChannel socket = SocketChannel.open();
        socket.configureBlocking(false);

        //打开选择器
        Selector selector =Selector.open();

        //2.连接服务端
        socket.register(selector, SelectionKey.OP_CONNECT);
        socket.connect(serverAddress);

        while(true){
            selector.select();

            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                //可连接事件
                if(selectionKey.isConnectable()){
                    if(socket.isConnectionPending()){
                        socket.finishConnect();
                        System.out.println("客户端连接完成");

                        String sendMsg="Hello Sever";
                        sendBuffer.clear();
                        sendBuffer.put(sendMsg.getBytes());
                        sendBuffer.flip();

                        socket.write(sendBuffer);
                    }
                    socket.register(selector,SelectionKey.OP_READ);
                }

                //可读事件
                if(selectionKey.isReadable()){
                    receiveBuffer.clear();
                    int count = socket.read(receiveBuffer);
                    if(count>0){
                        String reciveMsg = new String(receiveBuffer.array(),0,count);
                        System.out.println("接收服务端的数据内容：\t "+reciveMsg);
                    }
                    socket.register(selector,SelectionKey.OP_WRITE);
                }

                //可写事件
                if(selectionKey.isWritable()){
                    String sendMsg="flag->"+flag++;

                    sendBuffer.clear();
                    sendBuffer.put(sendMsg.getBytes());
                    sendBuffer.flip();
                    socket.write(sendBuffer);

                    socket.register(selector,SelectionKey.OP_READ);
                }
            }
        }
    }

}
