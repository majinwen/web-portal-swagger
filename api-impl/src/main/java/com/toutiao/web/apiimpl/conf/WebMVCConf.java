package com.toutiao.web.apiimpl.conf;

import com.toutiao.web.apiimpl.conf.interceptor.LoginInterceptor;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * zhangjinglei 2017/9/8 下午6:27
 */
@Configuration
public class WebMVCConf extends WebMvcConfigurerAdapter {
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
//            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/pinyin/404");
//            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/pinyin/404");
//            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/pinyin/404");
            ErrorPage ftlErrorPage = new ErrorPage(TemplateException.class, "/pinyin/404");

            container.addErrorPages(ftlErrorPage);
        });
    }

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
//
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/mvc/**").addResourceLocations("classpath:/templates/");
        super.addResourceHandlers(registry);
    }


}
