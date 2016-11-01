package org.yyf.nioCombo.net;

import com.google.common.base.Throwables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;

/**
 * Created by tobi on 16-10-31.
 */
public class TaskHandler implements Runnable {
    Socket socket;

    public TaskHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintStream out = new PrintStream(socket.getOutputStream())) {
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                boolean isTime = "TIME".equalsIgnoreCase(temp);
                if (isTime) {
                    out.println("now time is :" + new Date());
                } else if (temp.equalsIgnoreCase("exit")) {
                    out.print("exit~bye!");
                    break;
                } else {
                    out.println(temp);
                }
            }
        } catch (IOException e) {
            Throwables.propagate(e);
        }
    }
}
