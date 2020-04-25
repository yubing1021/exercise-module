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
public class NIOClient {

    //缓冲区大小
    private int blockSize = 4096;

    //发送和接收数据缓冲区
    private ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
    private ByteBuffer receiveBuffer = ByteBuffer.allocate(blockSize);

    //Socket服务端连接信息
    private final static InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1",19090);

    //选择器
    private Selector selector;

    private int flag =0;

    public NIOClient() throws IOException {
        //1.连接注册操作
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        //打开选择器
        selector =Selector.open();

        //2.连接服务端
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(serverAddress);
    }

    /**
    *@Description: 监听事件
    *@Param: 
    *@return: 
    *@date: 2020/3/20
    */
    public void listener() throws IOException {
        while(true){

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()){
                SelectionKey selectionKey = selectionKeyIterator.next();
                selectionKeyIterator.remove();

                //
                handler(selectionKey);
            }
        }
    }

    /**
    *@Description: 业务逻辑处理
    *@Param:
    *@return:
    *@date: 2020/3/20
    */
    public void handler(SelectionKey selectionKey) throws IOException {

        //客户端
        SocketChannel clientSocketChannel=null;
        //接收内容
        String reciveContent;
        //发送内容
        String sendContent;

        //可连接事件
        if(selectionKey.isConnectable()){
            System.out.println(" >>>>> client connect...");
            clientSocketChannel = (SocketChannel) selectionKey.channel();
            if(clientSocketChannel.isConnectionPending()){
                clientSocketChannel.finishConnect();
                System.out.println(" >>>>> client connect finish...");

                sendContent="Hello Sever";
                sendBuffer.clear();
                sendBuffer.put(sendContent.getBytes());
                sendBuffer.flip();

                clientSocketChannel.write(sendBuffer);
            }
            clientSocketChannel.register(selector,SelectionKey.OP_READ);
        }
        //可读事件
        else if(selectionKey.isReadable()){
            clientSocketChannel = (SocketChannel) selectionKey.channel();

            receiveBuffer.clear();
            int count = clientSocketChannel.read(receiveBuffer);
            if(count>0){
                reciveContent = new String(receiveBuffer.array(),0,count);
                System.out.println(" <-【客户端】接收服务端的数据内容："+reciveContent);
                clientSocketChannel.register(selector,SelectionKey.OP_WRITE);
            }
        }
        //可写事件
        else if(selectionKey.isWritable()){
            clientSocketChannel = (SocketChannel) selectionKey.channel();
            sendContent="msg send to sever i = "+flag++;

            sendBuffer.clear();
            sendBuffer.put(sendContent.getBytes());
            sendBuffer.flip();

            clientSocketChannel.write(sendBuffer);
            System.out.println(" ->【客户端】发送数据给服务端内容："+sendContent);

            clientSocketChannel.register(selector,SelectionKey.OP_READ);
        }
    }

    public static void main(String[] args) throws IOException {
        NIOClient client = new NIOClient();
        client.listener();
    }

}
