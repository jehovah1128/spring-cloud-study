package com.study.cloud.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CloudInventoryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudInventoryServerApplication.class, args);
    }
}
