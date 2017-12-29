package com.toutiao.web.apiimpl.conf.freemarker;


import com.toutiao.web.common.util.StringUtil;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        String url="";
        if(arguments.size()>0){
            url = arguments.get(0).toString();
        }
        String city="";
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object router_city = request.getAttribute("__router_city");
        if(router_city==null){
            String strip = StringUtils.strip(request.getServletPath(), "/");
            String[] split = strip.split("/");
            if(split.length>0){
                city=split[0];
                request.setAttribute("__router_city",city);
            }
        }
        else {
            city=router_city.toString();
        }
        StringBuffer stringBuffer = new StringBuffer();
        if(StringUtils.isNotBlank(city)) {
            stringBuffer.append("/"+city);
        }
        stringBuffer.append("/"+StringUtils.strip(url,"/"));

        return stringBuffer.toString();
    }
}
