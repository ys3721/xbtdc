package cn.alip8.io.webproxy;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;

/**
 * @author yaoshuai
 * @date 2021-五月-19
 */
public class WebProxyFrontendHandler extends ChannelInboundHandlerAdapter {

    private String remoteHost;

    private int remotePort;

    private Channel outboundChannel;

    public WebProxyFrontendHandler(String remoteHost, int remotePort) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //in bound channel
        final NioSocketChannel inChannel = (NioSocketChannel) ctx.channel();
        Bootstrap b = new Bootstrap();
        b.group(ctx.channel().eventLoop())
                .channel(ctx.channel().getClass())
                .handler(null)
                .option(ChannelOption.AUTO_READ, false);
        ChannelFuture f = b.connect(remoteHost, remotePort);
        this.outboundChannel = (NioSocketChannel) f.channel();
        f.addListener((future) -> {
            if (future.isSuccess()) {
                inChannel.read();
            } else {
                inChannel.close();
            }
        });
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] reads = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(reads);
        String line = new String(reads, StandardCharsets.UTF_8);
        if (line.contains("Host:")) {
            line = "Host: 123.207.245.161:9090";
        }
        byte[] bs = (line + "\n").getBytes(StandardCharsets.UTF_8);
        System.out.print(new String(bs, StandardCharsets.UTF_8));
        ByteBuf needWrite = ctx.alloc().buffer(bs.length);
        needWrite.writeBytes(needWrite);
        this.outboundChannel.writeAndFlush(needWrite).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    // was able to flush out data, start to read the next chunk
                    ctx.channel().read();
                } else {
                    ChannelFutureListener.CLOSE.operationComplete(future);
                }
            }
        });
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        if (outboundChannel != null) {
            closeOnFlush(outboundChannel);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        closeOnFlush(ctx.channel());
    }

    /**
     * Closes the specified channel after all queued write requests are flushed.
     */
    static void closeOnFlush(Channel ch) {
        if (ch.isActive()) {
            ch.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }
}
