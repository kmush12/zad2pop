package com.company.zad2pop;

import org.springframework.boot.SpringApplication;

import java.util.Collections;

public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8081"));
        app.run(args);
    }
}
