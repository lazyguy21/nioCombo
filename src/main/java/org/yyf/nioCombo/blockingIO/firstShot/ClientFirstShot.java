package org.yyf.nioCombo.blockingIO.firstShot;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by tobi on 16-11-4.
 */
public class ClientFirstShot {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9999);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            int i=0;
            while (i <= 10){
                bufferedWriter.write(i++);
                System.out.println(i);
            }
        }
    }
}
