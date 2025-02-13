package com.azure97.socket.simple1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @description: SocketClient（客户端）：
 * @author: Azure
 * @date: 2024/9/23 周一 14:43
 * @Version 1.0
 **/

public class SocketClient {
    // 搭建客户端
    public static void main(String[] args) throws IOException {
        //创建客户端，并连接服务器
        Socket socket = new Socket("192.168.3.138", 5209);
        System.out.println("客户端启动成功");
        //获取控制台输入流
        BufferedReader out = new BufferedReader(new InputStreamReader(System.in));
        //通过socket输出流创建write推送功能对象
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        while (true) {
            //等待控制台不为空的输入流
            String str = out.readLine();
            if ("".equals(str)) {
                break;
            }
            //通过socket对象将字符串推送到服务器
            pw.println(str);
            //立刻刷新推送功能对象
           // pw.flush();
        } // 继续循环
        pw.close(); // 关闭Socket输出流
        socket.close(); // 关闭Socket
    }

}