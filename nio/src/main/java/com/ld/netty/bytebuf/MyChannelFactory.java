package com.ld.netty.bytebuf;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundInvoker;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.ProgressivePromise;
import io.netty.util.concurrent.Promise;
import io.netty.util.concurrent.ScheduledFuture;

import java.net.SocketAddress;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by liudun on 2018/1/15.
 */
public class MyChannelFactory {

    public static  Channel  getChannel(){
        Channel channel = new Channel() {
            @Override
            public ChannelId id() {
                return null;
            }

            @Override
            public EventLoop eventLoop() {
                return new EventLoop() {
                    @Override
                    public EventLoopGroup parent() {
                        return null;
                    }

                    @Override
                    public EventLoop next() {
                        return null;
                    }

                    @Override
                    public ChannelFuture register(Channel channel) {
                        return null;
                    }

                    @Override
                    public ChannelFuture register(ChannelPromise promise) {
                        return null;
                    }

                    @Override
                    public ChannelFuture register(Channel channel, ChannelPromise promise) {
                        return null;
                    }

                    @Override
                    public boolean inEventLoop() {
                        return false;
                    }

                    @Override
                    public boolean inEventLoop(Thread thread) {
                        return false;
                    }

                    @Override
                    public <V> Promise<V> newPromise() {
                        return null;
                    }

                    @Override
                    public <V> ProgressivePromise<V> newProgressivePromise() {
                        return null;
                    }

                    @Override
                    public <V> Future<V> newSucceededFuture(V result) {
                        return null;
                    }

                    @Override
                    public <V> Future<V> newFailedFuture(Throwable cause) {
                        return null;
                    }

                    @Override
                    public boolean isShuttingDown() {
                        return false;
                    }

                    @Override
                    public Future<?> shutdownGracefully() {
                        return null;
                    }

                    @Override
                    public Future<?> shutdownGracefully(long quietPeriod, long timeout, TimeUnit unit) {
                        return null;
                    }

                    @Override
                    public Future<?> terminationFuture() {
                        return null;
                    }

                    @Override
                    public void shutdown() {

                    }

                    @Override
                    public List<Runnable> shutdownNow() {
                        return null;
                    }

                    @Override
                    public Iterator<EventExecutor> iterator() {
                        return null;
                    }

                    @Override
                    public Future<?> submit(Runnable task) {
                        return null;
                    }

                    @Override
                    public <T> Future<T> submit(Runnable task, T result) {
                        return null;
                    }

                    @Override
                    public <T> Future<T> submit(Callable<T> task) {
                        return null;
                    }

                    @Override
                    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
                        return null;
                    }

                    @Override
                    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
                        return null;
                    }

                    @Override
                    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
                        return null;
                    }

                    @Override
                    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
                        return null;
                    }

                    @Override
                    public boolean isShutdown() {
                        return false;
                    }

                    @Override
                    public boolean isTerminated() {
                        return false;
                    }

                    @Override
                    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
                        return false;
                    }

                    @Override
                    public <T> List<java.util.concurrent.Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
                        return null;
                    }

                    @Override
                    public <T> List<java.util.concurrent.Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
                        return null;
                    }

                    @Override
                    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
                        return null;
                    }

                    @Override
                    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                        return null;
                    }

                    @Override
                    public void execute(Runnable command) {

                    }
                };
            }

            @Override
            public Channel parent() {
                return null;
            }

            @Override
            public ChannelConfig config() {
                return null;
            }

            @Override
            public boolean isOpen() {
                return false;
            }

            @Override
            public boolean isRegistered() {
                return false;
            }

            @Override
            public boolean isActive() {
                return false;
            }

            @Override
            public ChannelMetadata metadata() {
                return null;
            }

            @Override
            public SocketAddress localAddress() {
                return null;
            }

            @Override
            public SocketAddress remoteAddress() {
                return null;
            }

            @Override
            public ChannelFuture closeFuture() {
                return null;
            }

            @Override
            public boolean isWritable() {
                return false;
            }

            @Override
            public long bytesBeforeUnwritable() {
                return 0;
            }

            @Override
            public long bytesBeforeWritable() {
                return 0;
            }

            @Override
            public Unsafe unsafe() {
                return null;
            }

            @Override
            public ChannelPipeline pipeline() {
                ChannelPipeline pipeline = new ChannelPipeline() {
                    @Override
                    public ChannelPipeline addFirst(String name, ChannelHandler handler) {
                        return null;
                    }

                    @Override
                    public ChannelPipeline addFirst(EventExecutorGroup group, String name, ChannelHandler handler) {
                        return null;
                    }

                    @Override
                    public ChannelPipeline addLast(String name, ChannelHandler handler) {
                        return null;
                    }

                    @Override
                    public ChannelPipeline addLast(EventExecutorGroup group, String name, ChannelHandler handler) {
                        return null;
                    }

                    @Override
                    public ChannelPipeline addBefore(String baseName, String name, ChannelHandler handler) {
                        return null;
                    }

                    @Override
                    public ChannelPipeline addBefore(EventExecutorGroup group, String baseName, String name, ChannelHandler handler) {
                        return null;
                    }

                    @Override
                    public ChannelPipeline addAfter(String baseName, String name, ChannelHandler handler) {
                        return null;
                    }

                    @Override
                    public ChannelPipeline addAfter(EventExecutorGroup group, String baseName, String name, ChannelHandler handler) {
                        return null;
                    }

                    @Override
                    public ChannelPipeline addFirst(ChannelHandler... handlers) {
                        return null;
                    }

                    @Override
                    public ChannelPipeline addFirst(EventExecutorGroup group, ChannelHandler... handlers) {
                        return null;
                    }

                    @Override
                    public ChannelPipeline addLast(ChannelHandler... handlers) {
                        return null;
                    }

                    @Override
                    public ChannelPipeline addLast(EventExecutorGroup group, ChannelHandler... handlers) {
                        return null;
                    }

                    @Override
                    public ChannelPipeline remove(ChannelHandler handler) {
                        return null;
                    }

                    @Override
                    public ChannelHandler remove(String name) {
                        return null;
                    }

                    @Override
                    public <T extends ChannelHandler> T remove(Class<T> handlerType) {
                        return null;
                    }

                    @Override
                    public ChannelHandler removeFirst() {
                        return null;
                    }

                    @Override
                    public ChannelHandler removeLast() {
                        return null;
                    }

                    @Override
                    public ChannelPipeline replace(ChannelHandler oldHandler, String newName, ChannelHandler newHandler) {
                        return null;
                    }

                    @Override
                    public ChannelHandler replace(String oldName, String newName, ChannelHandler newHandler) {
                        return null;
                    }

                    @Override
                    public <T extends ChannelHandler> T replace(Class<T> oldHandlerType, String newName, ChannelHandler newHandler) {
                        return null;
                    }

                    @Override
                    public ChannelHandler first() {
                        return null;
                    }

                    @Override
                    public ChannelHandlerContext firstContext() {
                        return null;
                    }

                    @Override
                    public ChannelHandler last() {
                        return null;
                    }

                    @Override
                    public ChannelHandlerContext lastContext() {
                        return null;
                    }

                    @Override
                    public ChannelHandler get(String name) {
                        return null;
                    }

                    @Override
                    public <T extends ChannelHandler> T get(Class<T> handlerType) {
                        return null;
                    }

                    @Override
                    public ChannelHandlerContext context(ChannelHandler handler) {
                        return null;
                    }

                    @Override
                    public ChannelHandlerContext context(String name) {
                        return null;
                    }

                    @Override
                    public ChannelHandlerContext context(Class<? extends ChannelHandler> handlerType) {
                        return null;
                    }

                    @Override
                    public Channel channel() {
                        return null;
                    }

                    @Override
                    public List<String> names() {
                        return null;
                    }

                    @Override
                    public Map<String, ChannelHandler> toMap() {
                        return null;
                    }

                    @Override
                    public ChannelPipeline fireChannelRegistered() {
                        return null;
                    }

                    @Override
                    public ChannelPipeline fireChannelUnregistered() {
                        return null;
                    }

                    @Override
                    public ChannelPipeline fireChannelActive() {
                        return null;
                    }

                    @Override
                    public ChannelPipeline fireChannelInactive() {
                        return null;
                    }

                    @Override
                    public ChannelPipeline fireExceptionCaught(Throwable cause) {
                        return null;
                    }

                    @Override
                    public ChannelPipeline fireUserEventTriggered(Object event) {
                        return null;
                    }

                    @Override
                    public ChannelPipeline fireChannelRead(Object msg) {
                        return null;
                    }

                    @Override
                    public ChannelPipeline fireChannelReadComplete() {
                        return null;
                    }

                    @Override
                    public ChannelPipeline fireChannelWritabilityChanged() {
                        return null;
                    }

                    @Override
                    public ChannelPipeline flush() {
                        return null;
                    }

                    @Override
                    public ChannelFuture bind(SocketAddress localAddress) {
                        return null;
                    }

                    @Override
                    public ChannelFuture connect(SocketAddress remoteAddress) {
                        return null;
                    }

                    @Override
                    public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress) {
                        return null;
                    }

                    @Override
                    public ChannelFuture disconnect() {
                        return null;
                    }

                    @Override
                    public ChannelFuture close() {
                        return null;
                    }

                    @Override
                    public ChannelFuture deregister() {
                        return null;
                    }

                    @Override
                    public ChannelFuture bind(SocketAddress localAddress, ChannelPromise promise) {
                        return null;
                    }

                    @Override
                    public ChannelFuture connect(SocketAddress remoteAddress, ChannelPromise promise) {
                        return null;
                    }

                    @Override
                    public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
                        return null;
                    }

                    @Override
                    public ChannelFuture disconnect(ChannelPromise promise) {
                        return null;
                    }

                    @Override
                    public ChannelFuture close(ChannelPromise promise) {
                        return null;
                    }

                    @Override
                    public ChannelFuture deregister(ChannelPromise promise) {
                        return null;
                    }

                    @Override
                    public ChannelOutboundInvoker read() {
                        return null;
                    }

                    @Override
                    public ChannelFuture write(Object msg) {
                        return null;
                    }

                    @Override
                    public ChannelFuture write(Object msg, ChannelPromise promise) {
                        return null;
                    }

                    @Override
                    public ChannelFuture writeAndFlush(Object msg, ChannelPromise promise) {
                        return null;
                    }

                    @Override
                    public ChannelFuture writeAndFlush(Object msg) {
                        return null;
                    }

                    @Override
                    public ChannelPromise newPromise() {
                        return null;
                    }

                    @Override
                    public ChannelProgressivePromise newProgressivePromise() {
                        return null;
                    }

                    @Override
                    public ChannelFuture newSucceededFuture() {
                        return null;
                    }

                    @Override
                    public ChannelFuture newFailedFuture(Throwable cause) {
                        return null;
                    }

                    @Override
                    public ChannelPromise voidPromise() {
                        return null;
                    }

                    @Override
                    public Iterator<Map.Entry<String, ChannelHandler>> iterator() {
                        return null;
                    }
                };
                return pipeline;
            }

            @Override
            public ByteBufAllocator alloc() {
                return null;
            }

            @Override
            public Channel read() {
                return null;
            }

            @Override
            public Channel flush() {
                return null;
            }

            @Override
            public ChannelFuture bind(SocketAddress socketAddress) {
                return null;
            }

            @Override
            public ChannelFuture connect(SocketAddress socketAddress) {
                return null;
            }

            @Override
            public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress1) {
                return null;
            }

            @Override
            public ChannelFuture disconnect() {
                return null;
            }

            @Override
            public ChannelFuture close() {
                return null;
            }

            @Override
            public ChannelFuture deregister() {
                return null;
            }

            @Override
            public ChannelFuture bind(SocketAddress socketAddress, ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public ChannelFuture connect(SocketAddress socketAddress, ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress1, ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public ChannelFuture disconnect(ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public ChannelFuture close(ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public ChannelFuture deregister(ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public ChannelFuture write(Object o) {
                return null;
            }

            @Override
            public ChannelFuture write(Object o, ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public ChannelFuture writeAndFlush(Object o, ChannelPromise channelPromise) {
                return null;
            }

            @Override
            public ChannelFuture writeAndFlush(Object o) {
                return null;
            }

            @Override
            public ChannelPromise newPromise() {
                return null;
            }

            @Override
            public ChannelProgressivePromise newProgressivePromise() {
                return null;
            }

            @Override
            public ChannelFuture newSucceededFuture() {
                return null;
            }

            @Override
            public ChannelFuture newFailedFuture(Throwable throwable) {
                return null;
            }

            @Override
            public ChannelPromise voidPromise() {
                return null;
            }

            @Override
            public <T> Attribute<T> attr(AttributeKey<T> attributeKey) {
                return null;
            }

            @Override
            public <T> boolean hasAttr(AttributeKey<T> attributeKey) {
                return false;
            }

            @Override
            public int compareTo(Channel o) {
                return 0;
            }
        };
        return channel;
    }
}
