package org.yyf.nioCombo.channel;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by tobi on 16-10-31.
 */
public class SocketChannelTest {
    @Test
    public void testC() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);
    }
}
