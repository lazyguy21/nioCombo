package org.yyf.nioCombo.baseDemo;

import com.google.common.base.Charsets;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by tobi on 16-11-4.
 */
public class ServerMain {
    private static Selector selector;

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);

        selector = Selector.open();

        SelectionKey sk= serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                handle(selectionKey);
                iterator.remove();
            }
        }
    }

    private static void handle(SelectionKey selectionKey) {
        if (selectionKey.isValid()) {
            if (selectionKey.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                try {
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (selectionKey.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(2);
                Charset charset = Charsets.UTF_8;
                try {
                    StringBuilder content = new StringBuilder();
                    int counter;
                    /*正数为读到的字节大小
                    0说明读完了，流已经到最后了。
                    -1表明客户端已经关闭连接，此时应该调用close*/
                    while ((counter = socketChannel.read(byteBuffer)) > 0) {
                        byteBuffer.flip();
                        CharBuffer charBuffer = charset.decode(byteBuffer);
                        content.append(charBuffer.toString());
                        byteBuffer.clear();
                    }
                    System.out.println("content : " + content);
                    writeResponse(socketChannel,content.toString());
                    if (counter < 0) {
                        socketChannel.close();
                        System.out.println("close socketChannel");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void writeResponse(SocketChannel socketChannel, String response) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(("echo: "+response).getBytes());
        while (byteBuffer.hasRemaining()){
            try {
                socketChannel.write(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
