package org.yyf.nioCombo;

import org.junit.Before;
import org.junit.Test;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Created by tobi on 16-10-27.
 */
public class ByteBufferFirstShot {
    ByteBuffer buffer;

    @Before
    public void test() {
        buffer = ByteBuffer.allocate(100);
        buffer.put((byte) 'H').put((byte) 'e').put((byte) 'l').put((byte) 'l').put((byte) 'o');
        System.out.println(buffer.capacity());//100
        System.out.println(buffer.limit());//100
        System.out.println(buffer.position());//5
    }

    @Test
    public void test2() {
        //flip的功能＝＝buffer.limit(buffer.position()).position(0);
        Buffer flippedBuffer = buffer.flip();
        System.out.println(flippedBuffer.capacity());//100
        System.out.println(flippedBuffer.limit());//5
        System.out.println(flippedBuffer.position());//0
        //连续２次使用是不行的
        Buffer flippedBuffer2 = buffer.flip();
        System.out.println(flippedBuffer.capacity());//100
        System.out.println(flippedBuffer.limit());//0
        System.out.println(flippedBuffer.position());//0
    }
}
