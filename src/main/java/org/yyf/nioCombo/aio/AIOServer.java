package org.yyf.nioCombo.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by tobi on 16-11-11.
 */
public class AIOServer {
    public static void main(String[] args) {
        try {
            AsynchronousServerSocketChannel assc = AsynchronousServerSocketChannel.open();
            assc.bind(new InetSocketAddress(9999));
            assc.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
                @Override
                public void completed(AsynchronousSocketChannel result, Void attachment) {
                    try {
                        ByteBuffer byteBuffer = ByteBuffer.wrap("hello,I'm aio server!".getBytes());
                        Future<Integer> future = result.write(byteBuffer);
                        future.get();//这里会阻塞

                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void failed(Throwable exc, Void attachment) {

                }
            });
//            Attachment attachment = new Attachment();
//            attachment.assChannel = assc;
//            assc.accept(attachment, new ConnectionHandler());
            Thread.currentThread().join();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
