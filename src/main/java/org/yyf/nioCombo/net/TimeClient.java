package org.yyf.nioCombo.net;

import com.google.common.base.Throwables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by tobi on 16-10-31.
 */
public class TimeClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9999);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintStream out = new PrintStream(socket.getOutputStream())) {

            Scanner scanner = new Scanner(System.in);
            while (true) {
                out.println(scanner.nextLine());
                String s = reader.readLine();
                System.out.println(s);
            }


        } catch (IOException e) {
            Throwables.propagate(e);
        }

    }
}
