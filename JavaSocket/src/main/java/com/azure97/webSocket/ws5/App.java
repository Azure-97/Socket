package com.azure97.webSocket.ws5;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tio.websocket.starter.EnableTioWebSocketServer;

@SpringBootApplication
@EnableTioWebSocketServer
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("sw5");
    }
}

