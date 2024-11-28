package com.example.bkmallbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.example.bkmallbook","com.example.bkmallcommon"
})
public class BkmallBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BkmallBookApplication.class, args);
    }

}
