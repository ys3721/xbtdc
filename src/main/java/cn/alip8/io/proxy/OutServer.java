package cn.alip8.io.proxy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author yaoshuai
 * @date 2021-五月-16
 */
public class OutServer {

    private static OutServer outServer;

    private static MiddleService middleService;


    public static void main(String[] args) {
        outServer = new OutServer();
        middleService = new MiddleService();
        try {
            outServer.listen();
            middleService.listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listen() throws Exception {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap()
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new OutServerHandler())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(80).sync();
        } finally {

        }
    }

}
