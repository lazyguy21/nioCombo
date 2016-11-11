package org.yyf.nioCombo;

import org.yyf.nioCombo.aio.ConnectionHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

/**
 * Created by tobi on 16-11-1.
 */
public class AIOServer {
    private final static int PORT = 9090;

    private final static String HOST = "localhost";


    public static void main(String[] args) throws IOException, InterruptedException {
        AsynchronousServerSocketChannel assc = AsynchronousServerSocketChannel.open();
        assc.bind(new InetSocketAddress(PORT));
        System.out.println("server start at port :" + PORT);

        Attachment attachment = new Attachment();
        attachment.channelServer = assc;
//        assc.accept(attachment, new ConnectionHandler());
        Thread.currentThread().join();

    }
}
