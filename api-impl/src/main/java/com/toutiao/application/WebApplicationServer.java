package com.toutiao.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * zhangjinglei 2017/9/1 下午2:33
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.toutiao.web","com.toutiao.app","com.toutiao.appV2"})
public class WebApplicationServer {

    public static void main(String[] args) {
        SpringApplication.run(WebApplicationServer.class, args);
    }

}
