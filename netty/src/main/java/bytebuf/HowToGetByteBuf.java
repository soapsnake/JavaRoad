package bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

/**
 * Created by liudun on 2018/1/15.
 */
public class HowToGetByteBuf {
    //两种获取ByteBuf的方式

    public static void main(String[] args) {

        /**
         * ①通过ByteBufAllocator来获取
         *
         */
        Channel channel = MyChannelFactory.getChannel();

        //池化分配器
        ByteBufAllocator allocator = channel.alloc();

        //基于堆或者直接内存的buf
        ByteBuf buf1 = allocator.buffer(10086);

        //基于堆的buf
        ByteBuf buf2 = allocator.heapBuffer(10086);

        //基于直接内存的buf
        ByteBuf buf3 = allocator.directBuffer(10086);

        //混合buf
        ByteBuf buf4 = allocator.compositeBuffer(10086);

        //用于网络io的buf
        ByteBuf buf5 = allocator.ioBuffer();


        /**
         * ②通过Unpooled缓冲区来获取
         */
        //堆内存
        ByteBuf bf6 = Unpooled.buffer();

        ByteBuf bf7 = Unpooled.directBuffer();

        //包装类型
        ByteBuf bf8 = Unpooled.wrappedBuffer(bf6);
        //复制
        ByteBuf bf9 = Unpooled.copiedBuffer(bf7);

        System.out.println(ByteBufUtil.equals(bf7, bf9));

    }
}
