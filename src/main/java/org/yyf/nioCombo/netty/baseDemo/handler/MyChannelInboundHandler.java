package org.yyf.nioCombo.netty.baseDemo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by tobi on 2017/2/24.
 */
public class MyChannelInboundHandler extends ChannelInboundHandlerAdapter {
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("read data "+msg);
//        ByteBuf in = (ByteBuf) msg;
//        try {
//            while (in.isReadable()) { // (1)
//                System.out.print((char) in.readByte());
//                System.out.flush();
//            }
//        } finally {
//            ReferenceCountUtil.release(msg); // (2)
//        }
//    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("read data "+msg);
//        ByteBuf in = (ByteBuf) msg;
//        ByteBuf byteBuf = Unpooled.wrappedBuffer("hello".getBytes());
//        ctx.writeAndFlush(byteBuf);
//    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("read data "+msg);
        ByteBuf in = (ByteBuf) msg;
        if(in.readableBytes()>10){
            ByteBuf byteBuf = Unpooled.wrappedBuffer("hello".getBytes());
            ctx.writeAndFlush(byteBuf);
        }
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("received data finished!");
    }
}
