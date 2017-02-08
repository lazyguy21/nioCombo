package org.yyf.nioCombo.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 2017/2/7.
 */
public class AIOServer {
    public static void main(String[] args) throws InterruptedException {
        try {
            AsynchronousServerSocketChannel assc = AsynchronousServerSocketChannel.open();
            assc.bind(new InetSocketAddress(9999));

            Attachment attachment = new Attachment();
            attachment.assChannel = assc;

            assc.accept(attachment,new ConnectionHandler());

        } catch (IOException e) {
            e.printStackTrace();
        }
        TimeUnit.DAYS.sleep(1L);
    }
}
