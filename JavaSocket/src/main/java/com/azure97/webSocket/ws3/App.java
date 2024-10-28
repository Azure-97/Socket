package com.azure97.webSocket.ws3;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/9/23 周一 17:26
 * @Version 1.0
 **/
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(com.azure97.webSocket.ws3.App.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//
//            System.out.println("application ws3 started");
//
//        };
//    }
}

