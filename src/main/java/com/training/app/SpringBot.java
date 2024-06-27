package com.training.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringBot {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringBot.class)
            .build()
            .run(args);
    }
}
