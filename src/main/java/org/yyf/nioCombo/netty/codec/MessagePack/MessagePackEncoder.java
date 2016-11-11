package org.yyf.nioCombo.netty.codec.MessagePack;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by tobi on 16-11-2.
 */
public class MessagePackEncoder extends MessageToByteEncoder<Object>{
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        MessagePack messagePack = new MessagePack();
        byte[] bytes = messagePack.write(o);
        byteBuf.writeBytes(bytes);
    }
}
