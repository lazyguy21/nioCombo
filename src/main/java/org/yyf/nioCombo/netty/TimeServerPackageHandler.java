package org.yyf.nioCombo.netty;

import com.google.common.base.Charsets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by tobi on 16-11-1.
 */
public class TimeServerPackageHandler extends ChannelInboundHandlerAdapter {
    private int counter;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        int readableBytes = buf.readableBytes();
        byte[] req = new byte[readableBytes];
        buf.readBytes(req);
        String body = new String(req, Charsets.UTF_8);
        String substring = body.substring(0, body.length() - System.getProperty("line.separator").length());
        System.out.println(substring+" the counter is :"+ ++counter);
        ByteBuf resp = Unpooled.copiedBuffer(("echo :" + substring + System.getProperty("line.separator")).getBytes(Charsets.UTF_8));

        ctx.writeAndFlush(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        System.out.println("ahahah");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
    }

}
