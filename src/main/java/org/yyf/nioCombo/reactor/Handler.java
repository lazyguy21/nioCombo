package org.yyf.nioCombo.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by tobi on 16-11-3.
 */
public class Handler implements Runnable {
    static final int READING = 0, SENDING = 1;
    final SocketChannel socket;
    final SelectionKey sk;
    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);
    int state = READING;

    public Handler(Selector selector, SocketChannel c) throws IOException {
        socket = c;
        c.configureBlocking(false);
// Optionally try first read now
        sk = socket.register(selector, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    boolean inputIsComplete() {
        return true;/* ... */
    }

    boolean outputIsComplete() {
        return true; /* ... */
    }

    void process() { /* ... */ }

    @Override
    public void run() {
        try {
            if (state == READING) read();
            else if (state == SENDING) send();
        } catch (IOException ex) {
            /* ... */
        }
    }

    void read() throws IOException {
        System.out.println("read operation");
        int length = socket.read(input);
        byte[] bytes = new byte[length];
        input.flip();
        input.get(bytes);
        System.out.println(new String(bytes));
        if (inputIsComplete()) {
            process();
            state = SENDING;
// Normally also do first write now
            sk.interestOps(SelectionKey.OP_WRITE);
        }
    }

    void send() throws IOException {
        System.out.println("send operation");
        output.put("send back something!".getBytes());
        socket.write(output);
        if (outputIsComplete())
            sk.cancel();
    }
}
