package cn.alip8.io.hexproxy;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author: Yao Shuai
 * @date: 2021/5/17 20:15
 */
public class HexDumpProxyInitializer extends ChannelInitializer<SocketChannel> {

    private final String remoteHost;

    private final int remotePort;

    public HexDumpProxyInitializer(String remoteHost, int remotePort) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG), new HexDumpProxyFrountendHandler(remoteHost, remotePort));
    }
}
