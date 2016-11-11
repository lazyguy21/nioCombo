package org.yyf.nioCombo.netty.codec.LineBasedFreameDecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by tobi on 16-11-2.
 */
public class TimeServerDecoderHandler extends ChannelInboundHandlerAdapter {
    private int counter;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("recieved msg :"+msg+" counter : "+ ++counter);
        ByteBuf resp = Unpooled.copiedBuffer(("echo :" + body + System.getProperty("line.separator")).getBytes());
        ctx.writeAndFlush(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
