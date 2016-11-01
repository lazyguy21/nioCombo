package org.yyf.nioCombo.net;

import com.google.common.base.Throwables;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tobi on 16-10-31.
 */
public class TimServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true) {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new TaskHandler(socket));
                thread.start();
            }
        } catch (IOException e) {
            Throwables.propagate(e);
        }
    }
}
