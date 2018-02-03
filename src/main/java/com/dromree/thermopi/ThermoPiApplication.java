package com.dromree.thermopi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ThermoPiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThermoPiApplication.class, args);
    }
}
