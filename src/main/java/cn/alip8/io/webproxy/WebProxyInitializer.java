package cn.alip8.io.webproxy;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author: Yao Shuai
 * @date: 2021/5/18 14:42
 */
public class WebProxyInitializer extends ChannelInitializer<SocketChannel> {

    private final String remoteHost;
    private final int remotePort;

    // As we use inboundChannel.eventLoop() when building the Bootstrap this does not need to be volatile as
    // the outboundChannel will use the same EventLoop (and therefore Thread) as the inboundChannel.
    private Channel outboundChannel;

    public WebProxyInitializer(String remoteHost, int remotePort) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
<<<<<<< HEAD
        ch.pipeline().addLast(new LineBasedFrameDecoder(Integer.MAX_VALUE), new LoggingHandler(LogLevel.INFO),
=======
        ch.pipeline().addLast(new LineBasedFrameDecoder(Integer.MAX_VALUE),
                new LoggingHandler(LogLevel.INFO),
>>>>>>> 53cd2a7f6b4509853ba8c7eca6a5df712b698895
                new WebProxyFrontendHandler(remoteHost, remotePort));
    }

}
