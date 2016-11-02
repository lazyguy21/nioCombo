package org.yyf.nioCombo.netty.Decoder.LineBasedFreameDecoder;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by tobi on 16-11-2.
 * 带上解码器解决粘包，拆包问题
 */
public class TimeServerWithDecoder {
   public void bind(int port) throws InterruptedException {
       NioEventLoopGroup bossGroup = new NioEventLoopGroup();
       NioEventLoopGroup workerGroup = new NioEventLoopGroup();

       ServerBootstrap b = new ServerBootstrap();
       b.group(bossGroup, workerGroup)
               .channel(NioServerSocketChannel.class)
               .option(ChannelOption.SO_BACKLOG, 1024)
               .childHandler(new ChildChannelHandler());

       ChannelFuture channelFuture = b.bind(port).sync();

       channelFuture.channel().closeFuture().sync();

   }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
            socketChannel.pipeline().addLast(new StringDecoder());
            socketChannel.pipeline().addLast(new TimeServerDecoderHandler());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new TimeServerWithDecoder().bind(9999);
    }
}
