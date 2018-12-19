package com.toutiao.web.apiimpl.rest.user;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.sys.UserVerifyCodeRequest;
import com.toutiao.app.api.chance.response.user.UserLoginResponse;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.domain.user.UserBasicDoQuery;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.app.service.user.UserLoginService;
import com.toutiao.web.common.constant.syserror.UserInterfaceErrorCodeEnum;
import com.toutiao.web.common.restmodel.NashResult;
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
    private UserBasicInfoService userBasicInfoService;
    @Autowired
    private RedisSessionUtils redis;

    private CookieUtils cookieUtils;

    @RequestMapping(value = "/userVerifyCodeLogin", method = RequestMethod.POST)
    @ResponseBody
    public UserLoginResponse userVerifyCodeLogin(@Validated @RequestBody UserVerifyCodeRequest loginRequest,
                                          HttpServletRequest request, HttpServletResponse response) {

        UserBasicDoQuery userBasicDoQuery = new UserBasicDoQuery();
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        BeanUtils.copyProperties(loginRequest,userBasicDoQuery);
        UserBasicDo userBasicDo = userLoginService.checkUserVerifyCodeLogin(userBasicDoQuery,request,response);

        if(StringTool.isNotBlank(userBasicDo)){
            BeanUtils.copyProperties(userBasicDo,userLoginResponse);
            try {
                setCookieAndCache(loginRequest.getUserPhone(),userLoginResponse,request,response);
            } catch (Exception e) {
            }
        }
        return userLoginResponse;

    }



    private void setCookieAndCache(String phone,UserLoginResponse userLoginResponse,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        //删除保存的短信验证码
        redis.delKey(ServiceStateConstant.ALIYUN_SHORT_MESSAGE_LOGIN_REGISTER+"_"+phone);
        //清空redis中该手机号的失败次数
        redis.delKey(phone + RedisNameUtil.separativeSignCount);
        // 设置登录会员的cookie信息
        StringBuilder sb = new StringBuilder();
        String userJson = JSON.toJSONString(userLoginResponse);
        sb.append(userJson).append(RedisNameUtil.separativeSign);
        //用户信息加密
        String str = Com35Aes.encrypt(Com35Aes.KEYCODE, sb.toString());
        cookieUtils.setCookie(request, response, CookieUtils.COOKIE_NAME_USER, str);
        // 将登录用户放入缓存（此处缓存的数据及数据结构值得推敲，暂时先全部缓存起来）
        redis.set2(RedisObjectType.SYS_USER_MANAGER.getPrefix() + Constant.SYS_FLAGS
                + phone, userJson, RedisObjectType.SYS_USER_MANAGER.getExpiredTime());
    }

    /**
     * 根据cookie信息获取用户信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getUserCache", method = RequestMethod.GET)
    @ResponseBody
    public UserLoginResponse getUserCache(HttpServletRequest request, HttpServletResponse response) {

        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
        UserLoginResponse userLoginResponse = JSONObject.parseObject(user,UserLoginResponse.class);
        if(null != userLoginResponse){
            UserBasicDo userBasic =userBasicInfoService.queryUserBasic(userLoginResponse.getUserId());
            BeanUtils.copyProperties(userBasic,userLoginResponse);
        }else {
            Integer ss = UserInterfaceErrorCodeEnum.USER_NO_LOGIN.getValue();
        }
        return userLoginResponse;
    }
}
