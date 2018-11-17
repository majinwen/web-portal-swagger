package com.toutiao.web.apiimpl.conf;

//import com.toutiao.web.apiimpl.conf.interceptor.GlobalExceptionHandler;
import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Writer;

public class TemplateErrorHandler implements TemplateExceptionHandler {
    Logger logger = LoggerFactory.getLogger(TemplateErrorHandler.class);
    @Override
    public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {
        logger.error("模板报错",te);
        throw  te;
    }
}
