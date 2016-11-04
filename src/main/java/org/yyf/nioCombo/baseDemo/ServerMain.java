package org.yyf.nioCombo.baseDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
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

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                handle(selectionKey);
            }
        }
    }

    private static void handle(SelectionKey selectionKey) {
        if(selectionKey.isValid()){
            if(selectionKey.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                try {
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(selectionKey.isReadable()){
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(2);

                try {
                    int length;
                    StringBuilder sb = new StringBuilder();
                    while ((length=socketChannel.read(byteBuffer))!=-1||(length=socketChannel.read(byteBuffer))!=0){
                        byteBuffer.flip();
                        byte[] dst = new byte[length];
                        byteBuffer.get(dst);
                        sb.append(new String(dst));
                    }
                    System.out.println(sb);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
