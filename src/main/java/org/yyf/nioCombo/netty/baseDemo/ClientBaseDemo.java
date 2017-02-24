package org.yyf.nioCombo.netty.baseDemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by tobi on 2017/2/22.
 */
public class ClientBaseDemo {
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx,ByteBuf byteBuf) throws Exception {
                        byte[] dst = new byte[byteBuf.readableBytes()];
                        byteBuf.readBytes(dst);
                        System.out.println("Reveived data "+new String(dst));
                        byteBuf.clear();
                    }
                });

//        ChannelFuture channelFuture = bootstrap.connect("www.baidu.com", 80);
        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("localhost", 8989));

        channelFuture.syncUninterruptibly();

    }
}
