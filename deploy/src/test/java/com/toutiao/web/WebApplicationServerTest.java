package com.toutiao.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : zym
 * @date : 2018/11/21 18:02
 * @desc :
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = WebApplicationServer.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.cloud.config.enabled: false"
        })
public class WebApplicationServerTest {
    /**
     * Context loaders.
     */
    @Test
    public void contextLoaders() {
    }
}