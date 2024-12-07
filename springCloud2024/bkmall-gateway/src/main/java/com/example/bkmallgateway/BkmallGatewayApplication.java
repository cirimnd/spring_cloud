package com.example.bkmallgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class BkmallGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BkmallGatewayApplication.class, args);
    }

}
