package org.yyf.nioCombo.netty.baseDemo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by tobi on 2017/2/24.
 */
public class PreserveByteBufHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf buffer;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        buffer = ctx.alloc().buffer(4);

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        buffer.release();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf m = (ByteBuf) msg;
        buffer.writeBytes(m);
        m.release();

        if (buffer.readableBytes() >= 4) { // (3)
//            buffer.readBytes()
            ctx.close();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
