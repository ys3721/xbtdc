package cn.alip8.io.webproxy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: Yao Shuai
 * @date: 2021/5/18 14:24
 */
public class WebProxyServer {

    private static int localPort = Integer.parseInt(System.getProperty("localport", "8899"));
    private static int remotePort = Integer.parseInt(System.getProperty("remotePort", "80"));
    private static String remoteAddress = System.getProperty("remoteAddress", "www.baidu.com");

    public static void main(String[] args) throws Exception {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .handler(new WebProxyInitiailzer())
                    .option(ChannelOption.SO_BACKLOG, 150)
                    .childHandler(ChannelOption.AUTO_READ, false);
            b.bind(localPort).sync().channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
