package com.toutiao.appV2.apiimpl.userbasic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.domain.user.UserBasicDoQuery;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.app.service.user.UserLoginService;
import com.toutiao.appV2.api.userbasic.UserbasicApi;
import com.toutiao.web.common.constant.syserror.UserInterfaceErrorCodeEnum;
import com.toutiao.web.common.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.toutiao.appV2.model.userbasic.UserBasicResponse;
import com.toutiao.appV2.model.userbasic.UserLoginResponse;
import com.toutiao.appV2.model.userbasic.UserVerifyCodeRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:40:39.438Z")

@Controller
public class UserbasicApiController implements UserbasicApi {

    private static final Logger log = LoggerFactory.getLogger(UserbasicApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final HttpServletResponse response;

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserBasicInfoService userBasicInfoService;
    @Autowired
    private RedisSessionUtils redis;

    @org.springframework.beans.factory.annotation.Autowired
    public UserbasicApiController(ObjectMapper objectMapper, HttpServletRequest request, HttpServletResponse response) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.response = response;
    }

    @Override
    public ResponseEntity<UserLoginResponse> getUserCache() {
        try {
            String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
            com.toutiao.app.api.chance.response.user.UserLoginResponse userLoginResponse = JSONObject.parseObject(user, com.toutiao.app.api.chance.response.user.UserLoginResponse.class);
            if (null != userLoginResponse) {
                UserBasicDo userBasic = userBasicInfoService.queryUserBasic(userLoginResponse.getUserId());
                BeanUtils.copyProperties(userBasic, userLoginResponse);
                UserLoginResponse userLoginResponse1 = new UserLoginResponse();
                BeanUtils.copyProperties(userLoginResponse, userLoginResponse1);
                return new ResponseEntity<UserLoginResponse>(userLoginResponse1, HttpStatus.NOT_IMPLEMENTED);
            } else {
                return new ResponseEntity<UserLoginResponse>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type ", e);
            return new ResponseEntity<UserLoginResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> logout() {
        try {
            UserBasicDo userBasic = null;
            String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
            UserLoginResponse userLoginResponse = JSONObject.parseObject(user, UserLoginResponse.class);
            if (null != userLoginResponse) {
                userBasic = userBasicInfoService.queryUserBasic(userLoginResponse.getUserId());
            }
            if (null != userBasic) {
                clearCookieAndCache(request, response, userBasic.getPhone());
                return new ResponseEntity<String>("退出登录成功", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("退出登录失败", HttpStatus.NOT_IMPLEMENTED);
            }

        } catch (Exception e) {
            log.error("Couldn't serialize response for content type ", e);
            return new ResponseEntity<String>("系统异常", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<UserBasicResponse> queryUserBasic(@ApiParam(value = "") @Valid @RequestParam(value = "userId", required = false) Optional<String> userId) {
        try {
            UserBasicDo userBasicDo = userBasicInfoService.queryUserBasic(userId.get());
            UserBasicResponse userBasicResponse = new UserBasicResponse();
            BeanUtils.copyProperties(userBasicDo, userBasicResponse);
            return new ResponseEntity<UserBasicResponse>(userBasicResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type ", e);
            return new ResponseEntity<UserBasicResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<UserBasicResponse> queryUserBasicByRcId(@ApiParam(value = "") @Valid @RequestParam(value = "rcId", required = false) Optional<String> rcId) {
        try {
            UserBasicDo userBasicDo = userBasicInfoService.queryUserBasicByRcId(rcId.get());
            UserBasicResponse userBasicResponse = new UserBasicResponse();
            BeanUtils.copyProperties(userBasicDo, userBasicResponse);
            return new ResponseEntity<UserBasicResponse>(userBasicResponse, HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type ", e);
            return new ResponseEntity<UserBasicResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<UserBasicResponse> updateUserAvatar(@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile file, @ApiParam(value = "") @Valid @RequestParam(value = "userId", required = false) Optional<String> userId) {
        try {
            UserBasicDo userBasicDo = userBasicInfoService.updateUserAvatar(userId.get(),
                    file, request, response);
            UserBasicResponse userBasicResponse = new UserBasicResponse();
            BeanUtils.copyProperties(userBasicDo, userBasicResponse);
            return new ResponseEntity<UserBasicResponse>(userBasicResponse, HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type ", e);
            return new ResponseEntity<UserBasicResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<UserLoginResponse> userVerifyCodeLogin(@ApiParam(value = "loginRequest", required = true) @Valid @RequestBody UserVerifyCodeRequest loginRequest) {

        try {
            UserBasicDoQuery userBasicDoQuery = new UserBasicDoQuery();
            UserLoginResponse userLoginResponse = new UserLoginResponse();
            BeanUtils.copyProperties(loginRequest, userBasicDoQuery);
            UserBasicDo userBasicDo = userLoginService.checkUserVerifyCodeLogin(userBasicDoQuery, request, response);

            if (StringTool.isNotBlank(userBasicDo)) {
                BeanUtils.copyProperties(userBasicDo, userLoginResponse);
                try {
                    setCookieAndCache(loginRequest.getUserPhone(), userLoginResponse, request, response);
                } catch (Exception e) {
                }
            }
            return new ResponseEntity<UserLoginResponse>(userLoginResponse, HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type ", e);
            return new ResponseEntity<UserLoginResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 清除缓存数据
     *
     * @param request
     * @param response
     * @throws Exception
     */
    private void clearCookieAndCache(HttpServletRequest request, HttpServletResponse response, String phone) throws Exception {
        //从cookie中删除用户数据
        CookieUtils.deleteCookie(request, response, CookieUtils.COOKIE_NAME_USER);
        //删除redis中的用户数据
        redis.delKey(RedisObjectType.SYS_USER_MANAGER.getPrefix() + Constant.SYS_FLAGS
                + phone);
    }

    private void setCookieAndCache(String phone, UserLoginResponse userLoginResponse,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        //删除保存的短信验证码
        redis.delKey(ServiceStateConstant.ALIYUN_SHORT_MESSAGE_LOGIN_REGISTER + "_" + phone);
        //清空redis中该手机号的失败次数
        redis.delKey(phone + RedisNameUtil.separativeSignCount);
        // 设置登录会员的cookie信息
        StringBuilder sb = new StringBuilder();
        String userJson = JSON.toJSONString(userLoginResponse);
        sb.append(userJson).append(RedisNameUtil.separativeSign);
        //用户信息加密
        String str = Com35Aes.encrypt(Com35Aes.KEYCODE, sb.toString());
        CookieUtils.setCookie(request, response, CookieUtils.COOKIE_NAME_USER, str);
        // 将登录用户放入缓存（此处缓存的数据及数据结构值得推敲，暂时先全部缓存起来）
        redis.set2(RedisObjectType.SYS_USER_MANAGER.getPrefix() + Constant.SYS_FLAGS
                + phone, userJson, RedisObjectType.SYS_USER_MANAGER.getExpiredTime());
    }

}
