package com.toutiao.web.apiimpl.conf;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PathConf {
    @Bean
    public FilterRegistrationBean pathFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new PathFilter());
        registration.addUrlPatterns("/searchapiv2/*");
        registration.setName("pathFilter");
        registration.setOrder(1);
        return registration;
    }
}
