package org.yyf.nioCombo;

import java.util.concurrent.TimeUnit;

/**
 * Created by tobi on 16-11-1.
 */
public class Context {
    public static void main(String[] args) throws InterruptedException {
        MultiplexerTimeServer server = new MultiplexerTimeServer(9999);
        new Thread(server).start();
        TimeUnit.DAYS.sleep(1);
    }
}
