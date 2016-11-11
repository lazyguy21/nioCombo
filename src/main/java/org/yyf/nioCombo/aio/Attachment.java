package org.yyf.nioCombo.aio;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * Created by tobi on 16-11-11.
 */
public class Attachment {
    public AsynchronousServerSocketChannel assChannel;
    public AsynchronousSocketChannel asChannel;
    public boolean isReadMode;
    public ByteBuffer buffer;
    public SocketAddress clientAddr;
}
