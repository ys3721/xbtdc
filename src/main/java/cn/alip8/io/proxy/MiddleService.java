package cn.alip8.io.proxy;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: Yao Shuai
 * @date: 2021/5/17 11:00
 */
public class MiddleService {

    public void listen() throws Exception{
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap()
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new OutServerHandler())
                    .option(ChannelOption.SO_BACKLOG, 2)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(2052).sync();
        } finally {

        }
    }

    public void connect() throws Exception {
        EventLoopGroup boos = new NioEventLoopGroup(1);

        Bootstrap b = new Bootstrap()
                .group(boos)
                .channel(NioSocketChannel.class)
                .handler(new OutServerHandler());
        ChannelFuture future = b.connect("127.0.0.1", 2052).sync();

    }

    public void sendConnectionMsg(String hostName, int port) {

    }
}
