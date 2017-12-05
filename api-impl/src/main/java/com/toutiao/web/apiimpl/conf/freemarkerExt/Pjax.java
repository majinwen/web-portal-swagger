package com.toutiao.web.apiimpl.conf.freemarkerExt;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Pjax implements TemplateMethodModelEx {
    @Override
    public Object exec(List list) throws TemplateModelException {
        ServletRequestAttributes ra= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =  ra.getRequest();
        String header = request.getHeader("X-PJAX");
        if(StringUtils.isNoneBlank(header)){
            return false;
        }
        return true;
    }
}
