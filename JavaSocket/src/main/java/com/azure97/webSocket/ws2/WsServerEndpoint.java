package com.azure97.webSocket.ws2;


import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import jakarta.websocket.OnMessage;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import java.io.IOException;

@ServerEndpoint("/myWs")
@Component
public class WsServerEndpoint {

    /**
     * 连接成功
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("连接成功");
    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("连接关闭");
    }

    /**
     * 接收到消息
     *
     * @param text
     */
    @OnMessage
    public String onMsg(String text) throws IOException {
        return "servet 发送：" + text;
    }
}