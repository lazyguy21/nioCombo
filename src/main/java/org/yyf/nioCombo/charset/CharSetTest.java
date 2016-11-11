package org.yyf.nioCombo.charset;

import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by tobi on 16-11-11.
 */
public class CharSetTest {
    @Test
    public void test(){
        char c = '严';
        byte[] bytes = "严".getBytes(Charset.forName("iso-8859-1"));
        byte[] bytes2 = "严".getBytes(Charset.forName("utf-8"));
        System.out.println(Arrays.toString(bytes));
        System.out.println(Arrays.toString(bytes2));
    }
}
