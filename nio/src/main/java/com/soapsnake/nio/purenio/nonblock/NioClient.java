package com.soapsnake.nio.purenio.nonblock;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {

    public static void main(String[] args) {

        try {
            //打开一个通道
            SocketChannel socketChannel = SocketChannel.open();
            //发起连接
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 1111));

            // 发送请求
            ByteBuffer buffer = ByteBuffer.wrap("1234567890".getBytes());
            socketChannel.write(buffer);

            // 读取响应
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            int num;
            if ((num = socketChannel.read(readBuffer)) > 0) {
                readBuffer.flip();

                byte[] re = new byte[num];
                readBuffer.get(re);

                String result = new String(re, "UTF-8");
                System.out.println("返回值: " + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
