package org.yyf.nioCombo.netty.codec.MessagePack;

import com.google.common.collect.Lists;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tobi on 16-11-2.
 */
public class MessagePackDemo {
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = Lists.newArrayList("msgPack", "asdfa", "12312", "haha");
        MessagePack messagePack = new MessagePack();
        byte[] bytes = messagePack.write(list);
        List<String> result = messagePack.read(bytes, Templates.tList(Templates.TString));
        System.out.println(result);
    }
}
