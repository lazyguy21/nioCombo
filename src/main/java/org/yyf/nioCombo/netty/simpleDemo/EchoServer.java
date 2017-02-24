package org.yyf.nioCombo.netty.simpleDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by tobi on 2017/2/8.
 */
public class EchoServer {
    public  static void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new EchoServerHandler());
                    }
                });

        ChannelFuture f = b.bind(9999).sync();

        f.channel().closeFuture().sync();
    }

    public static void main(String[] args) throws InterruptedException {
        start();
    }
}
