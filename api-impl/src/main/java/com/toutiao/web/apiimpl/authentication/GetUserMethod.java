package com.toutiao.web.apiimpl.authentication;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.api.chance.response.user.UserLoginResponse;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUserMethod implements TemplateMethodModelEx {



    @Override
    public Object exec(List list) throws TemplateModelException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        Object user = request.getAttribute("userLogin");
        if(!user.equals("请登录")){
            userLoginResponse = JSON.parseObject(user.toString(),UserLoginResponse.class);
//            UserBasicDo userBasic =userBasicInfoService.queryUserBasic(userLoginResponse.getUserId());
//            user = userBasic.getPhone();
            return userLoginResponse;
        }else{
            return "请登录";
        }

    }
}
