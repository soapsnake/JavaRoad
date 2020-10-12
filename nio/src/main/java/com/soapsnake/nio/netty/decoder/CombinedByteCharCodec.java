package com.soapsnake.nio.netty.decoder;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * Created by soapsnake on 2018/1/16.
 * <p>
 * 编解码器:是编码器和解码器的组合
 */
public class CombinedByteCharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {

    public CombinedByteCharCodec() {
        super(new ByteToCharDecoder(), new CharToByteEncoder());
    }
}
