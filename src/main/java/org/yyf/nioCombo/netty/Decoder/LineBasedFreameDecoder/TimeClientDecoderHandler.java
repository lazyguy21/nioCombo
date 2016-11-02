package org.yyf.nioCombo.netty.Decoder.LineBasedFreameDecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by tobi on 16-11-2.
 */
public class TimeClientDecoderHandler extends ChannelInboundHandlerAdapter {
    private int counter;
    private int writerCounter;
    private byte[] req;

    public TimeClientDecoderHandler() {
        req = ("QUERY TIME ORDER").getBytes();
//        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            System.out.println("writeCounter: "+ ++writerCounter);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println(msg + " counter :" + ++counter);
    }
}
