package com.toutiao.web.apiimpl.conf;
//
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
//
//import javax.servlet.MultipartConfigElement;

/**
 * zhangjinglei 2017/8/31 下午6:27
 * 上传文件 配置
 */
@Configuration
public class FileUploadConf {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置文件大小限制 ,超出设置页面会抛出异常信息，
        // 这样在文件上传的地方就需要进行异常信息的处理了;
        factory.setMaxFileSize("20MB"); // KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("22MB");
        // Sets the directory location where files will be stored.
        // factory.setLocation("路径地址");
        return factory.createMultipartConfig();
    }
}
