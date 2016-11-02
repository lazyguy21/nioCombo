package org.yyf.nioCombo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by tobi on 16-11-1.
 */
public class TimeClientPackageHandler extends ChannelInboundHandlerAdapter {
    private int sendCounter;
    private int receiveCounter;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            byte[] bytes = "QUERY TIME ORDER".getBytes();
            ByteBuf buffer = Unpooled.buffer(bytes.length);
            buffer.writeBytes(bytes);
            System.out.println("sendCounter : "+ ++sendCounter);
            ctx.writeAndFlush(buffer);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("Now is : " + body+" receiveCounter :"+ ++receiveCounter);
    }
}
