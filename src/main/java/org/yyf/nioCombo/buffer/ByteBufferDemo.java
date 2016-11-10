package org.yyf.nioCombo.buffer;

import org.junit.Test;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-11-8.
 */
public class ByteBufferDemo {
    @Test
    public void test() {
        byte[] content = "hello".getBytes();
        ByteBuffer byteBuffer = ByteBuffer.wrap(content);
        ByteBuffer duplicate = byteBuffer.duplicate();

        CharBuffer charBuffer = byteBuffer.asCharBuffer();
        String s = charBuffer.toString();
        System.out.println(s);
    }

    @Test
    public void testDirectByteBuffer() throws Exception {
        ByteBuffer.allocateDirect(Integer.MAX_VALUE);
        TimeUnit.DAYS.sleep(1);
    }

    @Test
    public void testDirectByteBuffer2() throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(Integer.MAX_VALUE);
        TimeUnit.SECONDS.sleep(10);
        ((DirectBuffer) byteBuffer).cleaner().clean();//主动释放直接内存
        TimeUnit.DAYS.sleep(10);
    }
}
