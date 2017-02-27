package org.yyf.nioCombo.netty.stickAndUnpackage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;

/**
 * Created by tobi on 2017/2/27.
 */
public class MyClientOutboundHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {

            byte[] bytes = "QUERY TIME ORDER".getBytes();
            ByteBuf buffer = Unpooled.buffer(bytes.length);
            buffer.writeBytes(bytes);
            System.out.println("sendCounter : "+ i);
            ctx.writeAndFlush(buffer);
        }
    }
}
