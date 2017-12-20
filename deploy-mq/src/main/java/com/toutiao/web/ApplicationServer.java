package com.toutiao.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ApplicationServer {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationServer.class, args);
    }

}
