package org.yyf.nioCombo.aio;

import org.yyf.nioCombo.ReadWriteHandler;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

/**
 * Created by tobi on 16-11-1.
 */
public class ConnectionHandler implements CompletionHandler<AsynchronousSocketChannel, Attachment> {

    @Override
    public void completed(AsynchronousSocketChannel channelClient, Attachment att) {
        SocketAddress clientAddr = null;
        try {
            clientAddr = channelClient.getRemoteAddress();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Accepted connection from %s%n", clientAddr);

        att.assChannel.accept(att, this);

        System.out.println("hahaha");
        Attachment newAtt = new Attachment();
        newAtt.assChannel = att.assChannel;
        newAtt.asChannel = channelClient;
        newAtt.isReadMode = true;
        newAtt.buffer = ByteBuffer.allocate(2048);
        newAtt.clientAddr = clientAddr;
        ReadWriteHandler rwh = new ReadWriteHandler();
        channelClient.read(newAtt.buffer, newAtt,new ClientHandler());

    }

    @Override
    public void failed(Throwable exc, Attachment attachment) {
        System.out.println("Failed to accept connection");
    }


}
