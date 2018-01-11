package com.toutiao.web.apiimpl.conf;

import cn.org.rapid_framework.freemarker.directive.BlockDirective;
import cn.org.rapid_framework.freemarker.directive.ExtendsDirective;
import cn.org.rapid_framework.freemarker.directive.OverrideDirective;
import com.toutiao.web.apiimpl.authentication.GetUserMethod;
import com.toutiao.web.apiimpl.conf.freemarker.Router;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

/**
 * zhangjinglei 2017/10/30 下午5:23
 */
@Configuration
public class FreeMarkerConfig {
    Logger logger = LoggerFactory.getLogger(FreeMarkerConfig.class);
    @Autowired
    freemarker.template.Configuration configuration;

    @Value("${static.url}")
    private String staticUrl;

    @Value("${static.version}")
    private String staticVersion;
    @Value("${qiniu.img_domain}")
    private String qiniuImage;

    @PostConstruct
    public void setSharedVariable(){
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setNumberFormat("#");
        configuration.setSharedVariable("block", new BlockDirective());
        configuration.setSharedVariable("override", new OverrideDirective());
        configuration.setSharedVariable("extends", new ExtendsDirective());
        try {
            configuration.setSharedVariable("staticurl", staticUrl);
            configuration.setSharedVariable("qiniuimage",qiniuImage);
            configuration.setSharedVariable("staticversion", staticVersion);
            configuration.setSharedVariable("getUser",new GetUserMethod());
            configuration.setSharedVariable("router_city",new Router());

        }
        catch (Exception e){
            logger.error("freemarker 共享变量 staticurl 初始化失败",e);
        }
    }
}
