package com.toutiao.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * zhangjinglei 2017/9/1 下午2:33
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.toutiao.web","com.toutiao.app"})
@EnableScheduling
public class WebApplicationServer {

    public static void main(String[] args) {
        SpringApplication.run(WebApplicationServer.class, args);
    }

}