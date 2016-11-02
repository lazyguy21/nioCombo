package org.yyf.nioCombo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by tobi on 16-11-1.
 */
public class TimeServer {
    public static void main(String[] args) throws InterruptedException {
        TimeServer timeServer = new TimeServer();
        timeServer.bind(9999);

    }
    public void bind(int port) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChildChannelHandler());
        //binding port
        ChannelFuture f = serverBootstrap.bind(port).sync();

        f.channel().closeFuture().sync();
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
//            socketChannel.pipeline().addLast(new TimeServerHandler());
            socketChannel.pipeline().addLast(new TimeServerPackageHandler());
        }
    }
}
