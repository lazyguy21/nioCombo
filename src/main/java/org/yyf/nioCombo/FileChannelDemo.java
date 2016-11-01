package org.yyf.nioCombo;

import com.google.common.io.Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-10-27.
 */
public class FileChannelDemo {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        URL resource = Resources.getResource("fortest.txt");
        URI uri = resource.toURI();
        File file = new File(uri);
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();


        ByteBuffer buffer;
        buffer = ByteBuffer.allocate(100);
        buffer.put((byte) 'H').put((byte) 'e').put((byte) 'l').put((byte) 'l').put((byte) 'o');
        System.out.println(buffer.capacity());//100
        System.out.println(buffer.limit());//100
        System.out.println(buffer.position());//5


        buffer.flip();
        channel.read(buffer);
        TimeUnit.SECONDS.sleep(2L);

        System.out.println(buffer.toString());
    }

    public void d() {
        URL resource = this.getClass().getClassLoader().getResource("");

    }
}
