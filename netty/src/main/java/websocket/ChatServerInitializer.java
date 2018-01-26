package websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by liudun on 2018/1/16.
 * netty-channel-initializer
 */
public class ChatServerInitializer extends ChannelInitializer<Channel>{
    private final ChannelGroup group;

    public ChatServerInitializer(ChannelGroup group){
        this.group = group;
    }


    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //将http请求编码为字节的handler
        pipeline.addLast(new HttpServerCodec());
        //写文件的handler
        pipeline.addLast(new ChunkedWriteHandler());
        //聚合一个http请求的所有分片为一个完整的请求,下一个handler收到的请求就是一个完整请求
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        //自定义用来处理普通httprequest请求的handler
        pipeline.addLast(new HttpRequestHandler("/ws"));
        //用来处理websocket握手和建立连接的handler
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //自定义用来处理websocket内容的handler
        pipeline.addLast(new TextWebSocketFrameHandler(group));
    }

}
