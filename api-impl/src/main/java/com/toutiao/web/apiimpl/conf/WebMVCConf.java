package com.toutiao.web.apiimpl.conf;

import com.toutiao.web.apiimpl.conf.resolvers.MoneyArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * zhangjinglei 2017/9/8 下午6:27
 */
@Configuration
public class WebMVCConf extends WebMvcConfigurerAdapter {

//    @Autowired
//    private LoginInterceptor loginInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new MoneyArgumentResolver());
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
//
//    }




}
