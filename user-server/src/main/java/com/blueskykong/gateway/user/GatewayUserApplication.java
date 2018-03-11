package com.blueskykong.gateway.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class GatewayUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayUserApplication.class, args);
    }

    @GetMapping("/test")
    public String test() {
        return "ok";
    }
}
