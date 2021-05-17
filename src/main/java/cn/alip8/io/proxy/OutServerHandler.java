package cn.alip8.io.proxy;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * @author yaoshuai
 * @date 2021-五月-16
 */
public class OutServerHandler extends ChannelInboundHandlerAdapter {

    private MiddleService middleService;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //如果建立了链接那么也去对面建立链接，但是对面是内网，通知不过去，只能先建立通知的链接
        InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
        middleService.sendConnectionMsg(address.getHostName(), address.getPort());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.fireChannelRead(msg);

    }

}