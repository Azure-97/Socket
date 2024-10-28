package com.azure97.webSocket.ws2;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/9/23 周一 23:15
 * @Version 1.0
 **/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocket
public class WebsocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpoint() {
        return new ServerEndpointExporter();
    }

}