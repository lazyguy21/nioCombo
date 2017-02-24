package org.yyf.nioCombo.netty.baseDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.yyf.nioCombo.netty.baseDemo.handler.MyChannelInboundHandler;

import java.net.InetSocketAddress;

/**
 * Created by tobi on 2017/2/22.
 */
public class ServerBaseDemo {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                                      @Override
                                      protected void initChannel(SocketChannel ch) throws Exception {
                                          ch.pipeline().addLast(new MyChannelInboundHandler());
//                                          ch.pipeline().addLast(new TimeServerHandler() );
                                      }
                                  }

                    );

            ChannelFuture future = serverBootstrap.bind(new InetSocketAddress(8989));
//            future.syncUninterruptibly();
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
