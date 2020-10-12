package com.soapsnake.nio.netty.channelhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by soapsnake on 2018/1/15.
 */
public class DisCardInboundHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);

        //通过调用ReferenceCountUtil的release来释放资源
        ReferenceCountUtil.release(msg);
    }
}
