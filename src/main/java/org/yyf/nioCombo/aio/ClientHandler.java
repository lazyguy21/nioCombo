package org.yyf.nioCombo.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

/**
 * Created by tobi on 2017/2/7.
 */
public class ClientHandler implements CompletionHandler<Integer, Attachment> {
    @Override
    public void completed(Integer result, Attachment attachment) {
        System.out.println("read最后的结果 ：" + result);
        AsynchronousSocketChannel asChannel = attachment.asChannel;
        ByteBuffer buffer = attachment.buffer;

        buffer.flip();
        int limit = buffer.limit();
        byte bytes[] = new byte[limit];
        buffer.get(bytes, 0, limit);
        System.out.printf("Client at %s sends message: %s%n",
                attachment.clientAddr,
                new String(bytes, Charset.forName("utf-8")));

        buffer.rewind();
        asChannel.read(attachment.buffer, attachment, new ClientHandler());
    }

    @Override
    public void failed(Throwable exc, Attachment attachment) {

    }
}
