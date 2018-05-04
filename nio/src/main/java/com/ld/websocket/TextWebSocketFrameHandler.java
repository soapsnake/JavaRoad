package com.ld.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.apache.log4j.Logger;

/**
 * Created by liudun on 2018/1/16.
 * 用来处理websocket请求的handler,这个handle要跟在httprequesthandler的后边
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    public static final Logger LOGGER = Logger.getLogger(TextWebSocketFrameHandler.class);

    private final ChannelGroup group;

    public TextWebSocketFrameHandler(ChannelGroup group){
        this.group = group;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //增加引用计数防止消息被释放,把消息广播给group中的所有channel
        group.writeAndFlush(msg.retain());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE){
            //如果这个状态被触发,说明请求是websocket请求,那么移除http的handler以提高性能
            ctx.pipeline().remove(HttpRequestHandler.class);

            //通知所有已经连接的websocket客户端,新的客户端已经连接上了
            group.writeAndFlush(new TextWebSocketFrame("Client" + ctx.channel() + "joined"));
            //将这个新的channel加到channelgroup中,以使这个新的客户端也可以接收到消息
            group.add(ctx.channel());
        }else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
