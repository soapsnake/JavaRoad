package com.ld.thinkinjava.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by liudun on 2017/9/12.
 */
public class NioTest {

    public static void main(String[] args) {

        System.out.println("nio 测试....");

        try {
            SocketChannel channel = SocketChannel.open();

            channel.connect(new InetSocketAddress("10.4.226.212", 2181));


            channel.configureBlocking(false);

            Selector selector = Selector.open();

            SelectionKey key = channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            System.out.println(key.interestOps());


            System.out.println("test over ...");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
