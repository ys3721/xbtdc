package cn.alip8.io.webproxy;

import cn.alip8.io.NettyChannelCloseUtils;
import cn.alip8.io.hexproxy.HexDumpProxyFrontendHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: Yao Shuai
 * @date: 2021/5/20 16:23
 */
public class WebProxyBackendHandler extends ChannelInboundHandlerAdapter {

    private final NioSocketChannel inboundChannel;

    public WebProxyBackendHandler(NioSocketChannel inboundChannel) {
        this.inboundChannel = inboundChannel;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.read();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        inboundChannel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    ctx.read();
                } else {
                    future.channel().close();
                }
            }
        });
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        NettyChannelCloseUtils.closeOnFlush(inboundChannel);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        NettyChannelCloseUtils.closeOnFlush(ctx.channel());
    }
}
