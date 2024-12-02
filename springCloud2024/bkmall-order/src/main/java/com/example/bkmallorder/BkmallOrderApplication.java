package com.example.bkmallorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.bkmallcommon.client")
public class BkmallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BkmallOrderApplication.class, args);
    }

}
