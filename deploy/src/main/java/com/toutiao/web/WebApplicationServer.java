package com.toutiao.web;

import com.toutiao.web.common.util.Hmactest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * zhangjinglei 2017/9/1 下午2:33
 */
@SpringBootApplication
public class WebApplicationServer {

    public static void main(String[] args) throws Exception {
        System.out.print(Hmactest.s("zefweg","123"));
        SpringApplication.run(WebApplicationServer.class, args);
    }
}