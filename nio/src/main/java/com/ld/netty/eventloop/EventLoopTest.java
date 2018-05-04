package com.ld.netty.eventloop;


import com.ld.netty.bytebuf.MyChannelFactory;
import io.netty.channel.Channel;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by liudun on 2018/1/15.
 */
public class EventLoopTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Channel channel = MyChannelFactory.getChannel();
        ScheduledFuture<?> future = channel.eventLoop().scheduleAtFixedRate(
                () -> System.out.println("run every 60 seconds!!!!"),
                5,
                5,
                TimeUnit.SECONDS);
    }

}
