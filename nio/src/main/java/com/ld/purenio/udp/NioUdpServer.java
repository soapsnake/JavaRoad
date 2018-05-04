package com.ld.purenio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class NioUdpServer {

    public static void main(String[] args) {
        DatagramChannel channel = null;
        try {
            channel = DatagramChannel.open();
            channel.socket().bind(new InetSocketAddress(1112));
            ByteBuffer buf = ByteBuffer.allocate(48);
            channel.receive(buf);

            String newData = "New String to write to file..."
                    + System.currentTimeMillis();

            ByteBuffer buf2 = ByteBuffer.allocate(48);
            buf2.put(newData.getBytes());
            buf2.flip();
            int bytesSent = channel.send(buf2, new InetSocketAddress("jenkov.com", 80));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
