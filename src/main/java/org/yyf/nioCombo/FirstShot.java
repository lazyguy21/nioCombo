package org.yyf.nioCombo;

import com.google.common.base.Throwables;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by tobi on 16-10-31.
 */
public class FirstShot {
    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(9999)).configureBlocking(false);
            Selector selector = Selector.open();
//            New Thread(new ReactorTask()).start();
        } catch (IOException e) {
            Throwables.propagate(e);
        }
    }
}
