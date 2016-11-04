package org.yyf.nioCombo.blockingIO.backlog;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * Created by tobi on 16-11-4.
 */
public class ClientMain {
    private static int counter;
    private static List socketList = Lists.newArrayList();

    public static void main(String[] args) {
        try {
            while (true) {
                Socket socket = new Socket("localhost", 9999);
                socketList.add(socket);
                System.out.println("counter ：" + ++counter);
            }

        } catch (IOException e) {
            Throwables.propagate(e);
        }
    }
}
