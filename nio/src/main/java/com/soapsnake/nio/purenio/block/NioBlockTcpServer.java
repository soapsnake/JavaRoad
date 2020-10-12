package com.soapsnake.nio.purenio.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 阻塞式IO
 */
public class NioBlockTcpServer {

    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            //监听本地1111端口
            serverSocketChannel.socket().bind(new InetSocketAddress(1111));
            while (serverSocketChannel.isOpen()) {
                //一旦有一个TCP连接进来,就对应创建一个SocketChannle进行处理
                SocketChannel socketChannel = serverSocketChannel.accept();

                // 开启一个新的线程来处理这个请求，然后在 while 循环中继续监听 8080 端口
                new Thread(new SocketHandler(socketChannel)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
