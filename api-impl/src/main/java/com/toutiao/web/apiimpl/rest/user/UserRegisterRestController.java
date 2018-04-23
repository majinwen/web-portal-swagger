package com.toutiao.web.apiimpl.rest.user;


import com.toutiao.app.api.chance.request.sys.UserVerifyCodeRequest;
import com.toutiao.app.api.chance.response.user.UserLoginResponse;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.service.user.UserLoginService;
import com.toutiao.web.common.restmodel.InvokeResult;
import com.toutiao.web.common.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/rest/user")
public class UserRegisterRestController {

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private RedisSessionUtils redis;

    private CookieUtils cookieUtils;

    @RequestMapping(value = "/userVerifyCodeLogin", method = RequestMethod.POST)
    @ResponseBody
    public InvokeResult userVerifyCodeLogin(@Validated @RequestBody UserVerifyCodeRequest loginRequest,
                                             HttpServletRequest request, HttpServletResponse response) {

        UserBasicDo userBasicDo = new UserBasicDo();
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        BeanUtils.copyProperties(loginRequest,userBasicDo);
        userBasicDo = userLoginService.checkUserVerifyCodeLogin(userBasicDo,request,response);
        if(StringTool.isNotBlank(userBasicDo)){
            BeanUtils.copyProperties(userBasicDo,userLoginResponse);
            try {
                setCookieAndCache(loginRequest.getUserName(),request,response);
            } catch (Exception e) {
                InvokeResult.Fail(30002,"用户数据存储失败！");
            }
        }
        return InvokeResult.build(userLoginResponse);

    }



    private void setCookieAndCache(String phone,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        //清空redis中该手机号的失败次数
        redis.delKey(phone + RedisNameUtil.separativeSignCount);
        //删除保存的短信验证码
        redis.delKey(ServiceStateConstant.ALIYUN_SHORT_MESSAGE_LOGIN_REGISTER+"_"+phone);
        // 设置登录会员的cookie信息
        StringBuilder sb = new StringBuilder();
        sb.append(phone).append(RedisNameUtil.separativeSign);
        //用户信息加密
        String str = Com35Aes.encrypt(Com35Aes.KEYCODE, sb.toString());
        cookieUtils.setCookie(request, response, CookieUtils.COOKIE_NAME_User_LOGIN, str);
        // 将登录用户放入缓存（此处缓存的数据及数据结构值得推敲，暂时先全部缓存起来）
        redis.set2(RedisObjectType.SYS_USER_MANAGER.getPrefix() + Constant.SYS_FLAGS
                        + phone, phone, RedisObjectType.SYS_USER_MANAGER.getExpiredTime());
    }
}
