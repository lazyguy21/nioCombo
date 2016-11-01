package org.yyf.nioCombo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * Created by tobi on 16-11-1.
 */
public class AsyncTimeServer implements Runnable {
    private int port;
    private CountDownLatch countDownLatch;
    private AsynchronousServerSocketChannel asyncServerSocketChannel;

    public AsyncTimeServer(int port) {
        this.port = port;
        try {
            asyncServerSocketChannel = AsynchronousServerSocketChannel.open();
            asyncServerSocketChannel.bind(new InetSocketAddress(9999));
            System.out.println("The time server is start in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        countDownLatch = new CountDownLatch(1);
        doAccept();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doAccept() {
//        asyncServerSocketChannel.accept(this, new AcceptCompletionHandler());
    }
}
