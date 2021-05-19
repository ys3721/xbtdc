package cn.alip8.io.webproxy;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;

/**
 * @author yaoshuai
 * @date 2021-五月-19
 */
public class WebProxyFrontendHandler implements ChannelInboundHandlerAdapter {

    private String remoteHost;

    private int remotePort;

    public WebProxyFrontendHandler(String remoteHost, int remotePort) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Bootstrap b = new Bootstrap();
        b.group(ctx.channel().eventLoop())
                .channel(ctx.channel().getClass())
                .handler(new WebProxyBackendHandler())
                .option(ChannelOption.AUTO_READ, false);
        ChannelFuture f = b.connect(remoteHost, remotePort);
    }
}
