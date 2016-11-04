package org.yyf.nioCombo.blockingIO.backlog;

import com.google.common.base.Throwables;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-11-4.
 * 网络连接建立时的队列容量，java默认50，但是根据系统不同而不同，系统甚至可以忽略你传的backlog参数
 */
public class ServerMain {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999,10);
            while (true){
                TimeUnit.SECONDS.sleep(1L);
                Socket accept = serverSocket.accept();
                int port = accept.getPort();
                int localPort = accept.getLocalPort();
                System.out.println("port:"+port+"localPort: "+localPort);
            }
        } catch (IOException e) {
            Throwables.propagate(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
