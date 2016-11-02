package org.yyf.nioCombo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by tobi on 16-11-1.
 */
public class TimeClient {
    public static void main(String[] args) throws InterruptedException {
        TimeClient timeClient = new TimeClient();
        timeClient.connect();
    }

    public void connect() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel
                                .pipeline()
//                                .addLast(new TimeClientHanlder());
                                .addLast(new TimeClientPackageHandler());
                    }
                });

        ChannelFuture f = b.connect("localhost", 9999).sync();

        f.channel().closeFuture().sync();
    }
}
