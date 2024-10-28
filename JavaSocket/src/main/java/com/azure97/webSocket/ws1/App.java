package com.azure97.webSocket.ws1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/9/23 周一 22:26
 * @Version 1.0
 **/
public class App {
    final static int PORT = 8887; // 843 flash policy port
    public static void main(String[] args) throws InterruptedException, IOException {


        SocketServer s = new SocketServer(PORT);
        s.start();
        System.out.println("ChatServer started on port: " + s.getPort());

        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String in = sysin.readLine();
            s.broadcast(in);
            if (in.equals("exit")) {
                s.stop(1000);
                break;
            }
        }
    }
}
