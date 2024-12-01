package com.example.bkmallcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.example.bkmallorder.client")
@SpringBootApplication
public class BkmallCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(BkmallCartApplication.class, args);
    }

}
