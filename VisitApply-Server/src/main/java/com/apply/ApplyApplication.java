package com.apply;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class ApplyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplyApplication.class);
        System.out.println(LocalDateTime.now());
    }
}
