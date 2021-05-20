package cn.alip8.io.webproxy;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;

/**
 * @author: Yao Shuai
 * @date: 2021/5/20 15:40
 */
public class WebProxyFrontendHandler extends ChannelInboundHandlerAdapter {

    private final String remoteHost;
    private final int remotePort;

    private NioSocketChannel outboundChannel;

    public WebProxyFrontendHandler(String remoteHost, int remotePort) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //in bound channel
        final NioSocketChannel channel = (NioSocketChannel) ctx.channel();
        //connect target web, called outbound channel
        Bootstrap b = new Bootstrap();
        b.group(ctx.channel().eventLoop())
                .channel(ctx.channel().getClass())
                .handler(new WebProxyBackendHandler(channel))
                .option(ChannelOption.AUTO_READ, false);
        ChannelFuture f = b.connect(remoteHost, remotePort);
        outboundChannel = (NioSocketChannel) f.channel();
        f.addListener((future) -> {
            if (future.isSuccess()) {
                channel.read();
            } else {
                channel.close();
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
        outboundChannel.writeAndFlush(needWrite).addListener(new ChannelFutureListener() {
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
