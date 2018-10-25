package com.ld.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by liudun on 2018/1/15.
 */
public class ByteBufTest {

    public static void main(String[] args) {

        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf sliced = buf.slice(0, 15);
        System.out.println(sliced.toString(utf8));
        buf.setByte(0, 'J');
        System.out.println(buf.getByte(0) == sliced.getByte(0));

        System.out.println("isReadable: " + buf.isReadable());

        System.out.println("isWritable: " + buf.isWritable());

        System.out.println("readableBytes: " + buf.readableBytes());

        System.out.println("writableBytes: " + buf.writableBytes());

        System.out.println("maxCapacity: " + buf.maxCapacity());

        System.out.println("hasArray: " + buf.hasArray());

        System.out.println("buf.array: " + Arrays.toString(buf.array()));

    }
}
