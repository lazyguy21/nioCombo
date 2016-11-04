package org.yyf.nioCombo.blockingIO.firstShot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tobi on 16-11-4.
 */
public class ServerFirstShot {
    public static void main(String[] args) throws IOException {
        ServerSocket severSocket = new ServerSocket(9999);
        while (true){
            Socket socket = severSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String temp;
            while ((temp=bufferedReader.readLine())!=null){
                System.out.println(temp);
            }
        }
    }
}
