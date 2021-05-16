package com.soapsnake.mydubbo.client;

import com.soapsnake.mydubbo.msg.RpcResponse;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 
 * Created on 2020-10-09
 */
public class MyDubboClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcResponse rpcResponse = (RpcResponse)msg;
        Object returnVal = rpcResponse.getReturnObject();
        System.out.println("调用服务后异步获取执行结果，服务返回值为:"+returnVal);
    }
}
