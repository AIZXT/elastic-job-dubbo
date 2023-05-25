package com.zhang;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableDubbo
@SpringBootApplication
public class ProviderStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderStartApplication.class,args);
    }
}
