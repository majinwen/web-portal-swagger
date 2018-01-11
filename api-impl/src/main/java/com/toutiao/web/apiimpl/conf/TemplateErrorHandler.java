package com.toutiao.web.apiimpl.conf;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.Writer;

public class TemplateErrorHandler implements TemplateExceptionHandler {
    @Override
    public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {
        throw  te;
    }
}
