package org.yyf.nioCombo;

import com.google.common.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by tobi on 16-10-27.
 */
public class ChannelDemo {
    public static void main(String[] args) throws IOException {
        URL resource = Resources.getResource("fortest.txt");
        ReadableByteChannel src = Channels.newChannel(resource.openStream());
        try (InputStream inputStream = resource.openStream()) {
            WritableByteChannel dest = Channels.newChannel(System.out);
//            copy(src, dest);
            copyAlt(src,dest);
        }
    }

    public static void copy(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(100);
        while (src.read(buffer) != -1) {
            buffer.flip();
            dest.write(buffer);
            buffer.compact();
        }
        buffer.flip();
        while (buffer.hasRemaining())
            dest.write(buffer);
    }

    public static void copyAlt(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(2048);
        while (src.read(buffer) != -1) {
            buffer.flip();
            while (buffer.hasRemaining())
                dest.write(buffer);
            buffer.clear();
        }
    }

}
