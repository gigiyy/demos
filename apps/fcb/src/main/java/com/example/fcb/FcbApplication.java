package com.example.fcb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FcbApplication {

    public static void main(String[] args) {
        SpringApplication.run(FcbApplication.class, args);
    }

}
