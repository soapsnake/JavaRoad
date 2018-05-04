package com.ld.netty.channelhandler;

import com.ld.netty.bytebuf.MyChannelFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;

/**
 * Created by liudun on 2018/1/15.
 */
public class ChannelHandlerTest {

    public static void main(String[] args) {
        Channel channel = MyChannelFactory.getChannel();

        ChannelPipeline pipeline =  channel.pipeline();

        ChannelHandler firstHandler = new DisCardInboundHandler();

        pipeline.addFirst(firstHandler);

        System.out.println(pipeline.context(firstHandler));

        System.out.println(pipeline.names());
    }
}
