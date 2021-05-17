package cn.alip8.io.proxy;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: Yao Shuai
 * @date: 2021/5/17 14:20
 */
public class LocalServer {

    public static void main(String[] args) {

        LocalServer ls = new LocalServer();
        try {
            ls.connectOutServer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void connectOutServer() throws InterruptedException {
        EventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .group(worker)
                .channel(NioSocketChannel.class)
                .handler(new OutServerHandler());
        ChannelFuture f = bootstrap.connect("127.0.0.1", 2052).sync();
    }

}
