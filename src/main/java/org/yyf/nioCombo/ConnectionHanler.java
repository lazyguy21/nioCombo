package org.yyf.nioCombo;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by tobi on 16-11-1.
 */
public class ConnectionHanler implements CompletionHandler<AsynchronousSocketChannel, Attachment> {

    @Override
    public void completed(AsynchronousSocketChannel channelClient, Attachment att) {
        SocketAddress clientAddr = null;
        try {
            clientAddr = channelClient.getRemoteAddress();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Accepted connection from %s%n", clientAddr);

        att.channelServer.accept(att, this);

        Attachment newAtt = new Attachment();
        newAtt.channelServer = att.channelServer;
        newAtt.channelClient = channelClient;
        newAtt.isReadMode = true;
        newAtt.buffer = ByteBuffer.allocate(2048);
        newAtt.clientAddr = clientAddr;
        ReadWriteHandler rwh = new ReadWriteHandler();
        channelClient.read(newAtt.buffer, newAtt, rwh);
    }

    @Override
    public void failed(Throwable exc, Attachment attachment) {
        System.out.println("Failed to accept connection");
    }
}