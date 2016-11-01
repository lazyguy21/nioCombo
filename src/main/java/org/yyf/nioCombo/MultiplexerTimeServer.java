package org.yyf.nioCombo;

import com.google.common.base.Charsets;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Created by tobi on 16-10-31.
 */
public class MultiplexerTimeServer implements Runnable {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private volatile boolean stop;

    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("multiplexer time server start on port :" + port);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                selector.select(10000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeys) {
                    handleInput(selectionKey);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleInput(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isValid()) {
            if (selectionKey.isAcceptable()) {
                //返回的是SelectableChannel,自己强转为ServerSocketChannel.
                ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }
            if (selectionKey.isReadable()) {
                SocketChannel sc = (SocketChannel) selectionKey.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int readLength = sc.read(buffer);
                if (readLength > 0) {
                    byte[] content = new byte[readLength];
                    buffer.flip();
                    buffer.get(content);
                    String contentStr = new String(content);
                    response(sc, "echo :" + contentStr);
                } else if (readLength < 0) {
                    selectionKey.cancel();
                    sc.close();
                } else {
                    System.out.println("do nothing");
                }
            }
        }
    }

    private void response(SocketChannel sc, String response) throws IOException {
        byte[] responseBytes = response.getBytes(Charsets.UTF_8);
        ByteBuffer byteBuffer = ByteBuffer.wrap(responseBytes);
        sc.write(byteBuffer);

    }
}
