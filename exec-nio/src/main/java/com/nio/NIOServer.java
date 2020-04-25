package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @description: NIO Serer 服务端
 * @author: darben
 * @create: 2020-03-20 16:47
 */
public class NIOServer {

    //缓冲区大小长度
    private int bockSize = 4096;

    //发送数据缓冲区
    private ByteBuffer sendBuffer=ByteBuffer.allocate(bockSize);

    //接收数据缓冲区
    private ByteBuffer receiveBuffer=ByteBuffer.allocate(bockSize);

    //选择器
    private Selector selector;

    private int flag =1;

    //构造，初始化工作
    public NIOServer(int port) throws IOException {
        //服务端Channel
        ServerSocketChannel serverSocketChannel =ServerSocketChannel.open();
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //获取服务端Socket，绑定服务端IP+端口
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress("localhost",port));

        //初始化、打开选择器
        selector=Selector.open();
        //绑定监听接收事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端开启了");
        System.out.println("====================================================");
    }

    /**
    *@Description: 监听
    *@Param: 
    *@return: 
    *@date: 2020/3/20
    */
    public void listener() throws IOException {
        while (true){
            int select = selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                //业务逻辑处理
                handler(selectionKey);
            }
        }
    }

    /**
    *@Description: 业务处理
    *@Param:
    *@return:
    *@date: 2020/3/20
    */
    public void handler(SelectionKey selectionKey) throws IOException {

        //对selectionKey的事件判断
        //可接收事件状态
        if(selectionKey.isAcceptable()){
            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socket = server.accept();
            socket.configureBlocking(false);
            socket.register(selector,SelectionKey.OP_READ);

            String msg ="欢迎连接到服务端";
            socket.write(ByteBuffer.wrap(msg.getBytes()));

            InetSocketAddress address = (InetSocketAddress) socket.getRemoteAddress();
            System.out.println("客户端已连接");
            System.out.println("客户端连接信息\t"+ address.getHostString() +":"+address.getPort()+"\t");
            System.out.println("====================================================");
        }

        //可读事件状态
        if(selectionKey.isReadable()){
            SocketChannel socket= (SocketChannel) selectionKey.channel();
            int count = socket.read(receiveBuffer);
            if(count>0){
                String reciveMsg = new String(receiveBuffer.array(),0,count);
                System.out.println("接收客户端的消息：\t"+reciveMsg);
            }
            //注册写事件
            socket.register(selector,SelectionKey.OP_WRITE);
        }

        //可写事件状态
        if(selectionKey.isWritable()){
            SocketChannel socket= (SocketChannel) selectionKey.channel();
            String sendMsg="msg->"+ (flag ++);
            sendBuffer.clear();
            sendBuffer.put(sendMsg.getBytes());
            sendBuffer.flip();//写到缓冲区

            socket.write(sendBuffer);

            //注册读事件
            socket.register(selector,SelectionKey.OP_READ);
        }
    }

    public static void main(String[] args) throws IOException {
        int port = 8091;
        NIOServer nioServer =new NIOServer(port);
        nioServer.listener();
    }

}
