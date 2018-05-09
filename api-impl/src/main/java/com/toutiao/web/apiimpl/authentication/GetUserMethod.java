package com.toutiao.web.apiimpl.authentication;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.response.user.UserLoginResponse;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.service.user.UserBasicInfoService;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUserMethod implements TemplateMethodModelEx {

    @Autowired
    private UserBasicInfoService userBasicInfoService;

    @Override
    public Object exec(List list) throws TemplateModelException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Object user = request.getAttribute("userLogin");
        if(!user.equals("请登录")){
            UserLoginResponse userLoginResponse = JSON.parseObject(user.toString(),UserLoginResponse.class);

            user = userLoginResponse.getUserName();
        }
        return user;
    }
}
