package com.azure97.socket.simple2;

/**
 * @description:  SocketClient（客户端）
 * @author: Azure
 * @date: 2024/9/23 周一 15:20
 * @Version 1.0
 **/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    // 搭建客户端
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.3.138", 5219);
        System.out.println("客户端启动成功");
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader out = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        while (true) {
            String str = out.readLine();
            pw.println(str);
            pw.flush();
            System.out.println("客户端说：" + str);
            System.out.println("服务器说："+in.readLine());
        }
    }

}