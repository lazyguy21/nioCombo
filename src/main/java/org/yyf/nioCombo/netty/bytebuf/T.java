package org.yyf.nioCombo.netty.bytebuf;

import com.google.common.base.Charsets;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.experimental.theories.suppliers.TestedOn;

/**
 * Created by tobi on 16-11-10.
 */
public class T {
    @Test
    public void testByteBuffer() throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        byteBuffer.put("我是一些内容".getBytes());

        byteBuffer.flip();
        byte[] container = new byte[byteBuffer.remaining()];
        byteBuffer.get(container);
        System.out.println(new String(container));

    }

    @Test
    public void test(){
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello".getBytes());
        for (int i = 0; i < byteBuf.capacity(); i++) {
            byte aByte = byteBuf.getByte(i);
            System.out.println(aByte);
            System.out.println((char) aByte);

        }
    }

    /**
     * slice只是赋值index和引用
     * @throws Exception
     */
    @Test
    public void testSlice() throws Exception {
        Charset utf8 = Charset.forName("utf-8");
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello,netty !", utf8);
        ByteBuf slicedByteBuf = byteBuf.slice(0, 5);

        byteBuf.setByte(0, 'm');

        String s = byteBuf.toString(utf8);
        String s1 = slicedByteBuf.toString(utf8);
        System.out.println(s);
        System.out.println(s1);
    }

    /**
     * 深copy
     * @throws Exception
     */
    @Test
    public void testCopy() throws Exception {
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello,copy", Charsets.UTF_8);
        ByteBuf copy = byteBuf.copy();
        byteBuf.setByte(0, 'm');

        String s = byteBuf.toString(Charsets.UTF_8);
        System.out.println(s);

        String s1 = copy.toString(Charsets.UTF_8);
        System.out.println(s1);
    }

    @Test
    public void testNew() throws Exception {

    }
}
