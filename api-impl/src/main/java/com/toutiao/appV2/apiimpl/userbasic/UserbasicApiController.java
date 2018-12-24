package com.toutiao.appV2.apiimpl.userbasic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.service.Captcha;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.domain.user.UserBasicDoQuery;
import com.toutiao.app.domain.user.WXUserBasicDo;
import com.toutiao.app.service.sys.ShortMessageService;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.app.service.user.UserLoginService;
import com.toutiao.appV2.api.userbasic.UserbasicApi;
import com.toutiao.appV2.model.StringDataResponse;
import com.toutiao.appV2.model.userbasic.*;
import com.toutiao.web.common.constant.syserror.UserInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.*;
import com.toutiao.web.dao.entity.admin.UserSubscribeEtc;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:40:39.438Z")

@Controller
public class UserbasicApiController implements UserbasicApi {

    private static final Logger log = LoggerFactory.getLogger(UserbasicApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final HttpServletResponse response;

    @Value("${qiniu.img_wapapp_domain}")
    public String headPicPath;

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserBasicInfoService userBasicInfoService;
    @Autowired
    private RedisSessionUtils redis;
    @Autowired
    private ShortMessageService shortMessageService;

    @org.springframework.beans.factory.annotation.Autowired
    public UserbasicApiController(ObjectMapper objectMapper, HttpServletRequest request, HttpServletResponse response) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.response = response;
    }

    @Override
    public ResponseEntity<UserLoginResponse> getUserCache() {
        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
        UserLoginResponse userLoginResponse = JSONObject.parseObject(user, UserLoginResponse.class);
        if (null != userLoginResponse) {
            UserBasicDo userBasic = userBasicInfoService.queryUserBasic(userLoginResponse.getUserId());
            if(userBasic.getAvatar().indexOf("http:")==-1){
                userBasic.setAvatar(headPicPath + "/" + userBasic.getAvatar());
            }
            BeanUtils.copyProperties(userBasic, userLoginResponse);
            UserLoginResponse userLoginResponse1 = new UserLoginResponse();
            BeanUtils.copyProperties(userLoginResponse, userLoginResponse1);
            return new ResponseEntity<UserLoginResponse>(userLoginResponse1, HttpStatus.OK);
        } else {
            throw new BaseException(UserInterfaceErrorCodeEnum.QUERY_USER_BASIC_ERROR, "用户不存在");
        }
    }

    @Override
    public ResponseEntity<StringDataResponse> logout() throws Exception {
        UserBasicDo userBasic = null;
        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
        UserLoginResponse userLoginResponse = JSONObject.parseObject(user, UserLoginResponse.class);
        if (null != userLoginResponse) {
            userBasic = userBasicInfoService.queryUserBasic(userLoginResponse.getUserId());
            clearCookieAndCache(request, response, userBasic.getPhone());
            StringDataResponse stringDataResponse = new StringDataResponse();
            stringDataResponse.setData("退出登录成功");
            return new ResponseEntity<StringDataResponse>(stringDataResponse, HttpStatus.OK);
        } else {
            throw new BaseException(UserInterfaceErrorCodeEnum.USER_NO_LOGIN);
        }

    }

    @Override
    public ResponseEntity<UserBasicResponse> queryUserBasic(@ApiParam(value = "用户id", required = true) @Valid @RequestParam(value = "userId", required = true) String userId) {
        UserBasicDo userBasicDo = userBasicInfoService.queryUserBasic(userId);
        if (StringTool.isNotBlank(userBasicDo)) {
            UserBasicResponse userBasicResponse = new UserBasicResponse();
            BeanUtils.copyProperties(userBasicDo, userBasicResponse);
            if (StringUtils.isNotEmpty(userBasicDo.getUnionid())){
                userBasicResponse.setIsWxBind(true);
            }
            return new ResponseEntity<UserBasicResponse>(userBasicResponse, HttpStatus.OK);
        } else {
            throw new BaseException(UserInterfaceErrorCodeEnum.QUERY_USER_BASIC_ERROR, "用户不存在");
        }
    }

    @Override
    public ResponseEntity<UserBasicResponse> queryUserBasicByRcId(@ApiParam(value = "") @Valid @RequestParam(value = "rcId", required = false) Optional<String> rcId) {
        UserBasicDo userBasicDo = userBasicInfoService.queryUserBasicByRcId(rcId.get());
        if (StringTool.isNotBlank(userBasicDo)) {
            UserBasicResponse userBasicResponse = new UserBasicResponse();
            BeanUtils.copyProperties(userBasicDo, userBasicResponse);
            if (StringUtils.isNotEmpty(userBasicDo.getUnionid())){
                userBasicResponse.setIsWxBind(true);
            }
            return new ResponseEntity<UserBasicResponse>(userBasicResponse, HttpStatus.OK);
        } else {
            throw new BaseException(UserInterfaceErrorCodeEnum.QUERY_USER_BASIC_ERROR, "用户不存在");
        }
    }

    @Override
    public ResponseEntity<UserBasicResponse> updateUserAvatar(@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile file, @ApiParam(value = "") @Valid @RequestParam(value = "userId", required = false) Optional<String> userId) {

        String userString = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
        UserLoginResponse user = JSONObject.parseObject(userString, UserLoginResponse.class);
        if (null != user) {
            UserBasicDo userBasicDo = userBasicInfoService.updateUserAvatar(user.getUserId(),
                    file, request, response);
            UserBasicResponse userBasicResponse = new UserBasicResponse();
            BeanUtils.copyProperties(userBasicDo, userBasicResponse);
            return new ResponseEntity<UserBasicResponse>(userBasicResponse, HttpStatus.OK);
        } else {
            throw new BaseException(UserInterfaceErrorCodeEnum.USER_NO_LOGIN);
        }
    }

    @Override
    public ResponseEntity<UserLoginResponse> userVerifyCodeLogin(@ApiParam(value = "loginRequest", required = true) @Valid @RequestBody UserVerifyCodeRequest loginRequest) {
        UserBasicDoQuery userBasicDoQuery = new UserBasicDoQuery();
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        UserBasicDo userBasicDo = new UserBasicDo();
        BeanUtils.copyProperties(loginRequest, userBasicDoQuery);
        String type = userBasicDoQuery.getType();
        if("1".equals(type)||"2".equals(type)||"3".equals(type)){
            userBasicDo = userLoginService.checkUserVerifyCodeBindWXLogin(userBasicDoQuery, request, response);
        }else {
            userBasicDo = userLoginService.checkUserVerifyCodeLogin(userBasicDoQuery, request, response);
        }

        if (StringTool.isNotBlank(userBasicDo)) {
            BeanUtils.copyProperties(userBasicDo, userLoginResponse);
            if (null == userLoginResponse.getIsWxBind()){
                userLoginResponse.setIsWxBind(false);
            }
            try {
                if (StringTool.isNotEmpty(userBasicDoQuery.getUnionid())){
                    setCookieAndCache(loginRequest.getUserPhone(), userLoginResponse, request, response, 1);
                    //unionid加密
                    String str = Com35Aes.encrypt(Com35Aes.KEYCODE, (userLoginResponse.getUnionid()+RedisNameUtil.separativeSign+"加密").toString());
                    userLoginResponse.setUnionid(str);
                }else {
                    setCookieAndCache(loginRequest.getUserPhone(), userLoginResponse, request, response, 0);
                }
                return new ResponseEntity<UserLoginResponse>(userLoginResponse, HttpStatus.OK);
            } catch (Exception e) {
                throw new BaseException(UserInterfaceErrorCodeEnum.QUERY_USER_BASIC_ERROR, "用户不存在");
            }

        } else {
            throw new BaseException(UserInterfaceErrorCodeEnum.QUERY_USER_BASIC_ERROR, "用户不存在");
        }
    }

    @Override
    public ResponseEntity<Void> produceImageCode(@ApiParam(value = "0:普通, 1:微信", required = true) @Valid @RequestParam(value = "type", defaultValue = "0") String type) {
        String accept = request.getHeader("Accept");
        String patchca = "";
        // 设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        ConfigurableCaptchaService cs = PatchcaImageUtils.getCs();
        try {
            Captcha captcha = cs.getCaptcha();
            OutputStream os = response.getOutputStream();
            //获取验证码
            patchca = captcha.getChallenge();
            //将验证码数据放到cookie中
            Cookie cookie = null;
            if ("1".equals(type)){
                cookie = CookieUtils.setCookie(request, response, "wxImageCode", patchca);
            }else {
                cookie = CookieUtils.setCookie(request, response, "imageCode", patchca);
            }
            response.addCookie(cookie);
            //将验证码传输到页面中
            ImageIO.write(captcha.getImage(), "png", os);
            os.flush();
            os.close();
        } catch (BaseException ex) {
            throw new BaseException(ex.getCode(), ex.getMsg());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LoginVerifyCodeResponse> sendLoginVerifyCode(@ApiParam(value = "loginVerifyCodeRequest", required = true) @Valid @RequestBody LoginVerifyCodeRequest loginVerifyCodeRequest) {
            try {
                String phone = loginVerifyCodeRequest.getPhone();
                Integer type = loginVerifyCodeRequest.getType();
                String nashResult = shortMessageService.sendVerifyCode(phone,type);
                LoginVerifyCodeResponse loginVerifyCodeResponse = new LoginVerifyCodeResponse();
                loginVerifyCodeResponse.setMsg(nashResult);
                return new ResponseEntity<>(loginVerifyCodeResponse, HttpStatus.OK);
            } catch (BaseException ex) {
                throw new BaseException(ex.getCode(), ex.getMsg());
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }

    @Override
    public ResponseEntity<Void> validateImageCode(@ApiParam(value = "图形验证码", required = true) @Valid @RequestParam(value = "pageCode", required = true) String pageCode,
                                                  @ApiParam(value = "0:普通,1:微信", required = true, defaultValue = "0") @Valid @RequestParam(value = "type", required = true) String type) {

        String accept = request.getHeader("Accept");

        String info = "";
        try {
            String code = "";
            if("1".equals(type)){
                code = CookieUtils.getCookie(request, response, "wxImageCode");
            }else {
                code = CookieUtils.getCookie(request, response, "imageCode");
            }
            if (code.toLowerCase().equals(pageCode.toLowerCase())) {
                info = Constant.YES;
            } else {
                info = Constant.NO;
            }
            response.getOutputStream().print(info);
            response.getOutputStream().close();
        } catch (BaseException ex) {
            throw new BaseException(ex.getCode(), ex.getMsg());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @Override
    public ResponseEntity<UserSubscribeEtcCountResponse> getUserSubscribeEtcCount() {

        UserSubscribeEtcCountResponse userSubscribeEtcCountResponse = new UserSubscribeEtcCountResponse();
        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
        userSubscribeEtcCountResponse.setCsAccount("service");

        if (null != user) {
            UserLoginResponse userLoginResponse = JSONObject.parseObject(user, UserLoginResponse.class);
            UserSubscribeEtc userSubscribeEtc = userBasicInfoService.getUserFavoriteEtcCount(Integer.parseInt(userLoginResponse.getUserId()));
            BeanUtils.copyProperties(userSubscribeEtc, userSubscribeEtcCountResponse);
        }
        return new ResponseEntity<UserSubscribeEtcCountResponse>(userSubscribeEtcCountResponse, HttpStatus.OK);
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
                                   HttpServletRequest request, HttpServletResponse response, Integer type) throws Exception {
        //删除保存的短信验证码
        if(type==1){
            redis.delKey(ServiceStateConstant.ALIYUN_SHORT_MESSAGE_BIND_WX_REGISTER + "_" + phone);
        }else {
            redis.delKey(ServiceStateConstant.ALIYUN_SHORT_MESSAGE_LOGIN_REGISTER + "_" + phone);
        }
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

    @Override
    public ResponseEntity<UnbindResponse> unbindweixin(){
        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
        UserLoginResponse userLoginResponse = JSONObject.parseObject(user, UserLoginResponse.class);
        UnbindResponse unbind = new UnbindResponse();
        if (StringTool.isNotEmpty(userLoginResponse)){
            UserBasicDo userBasic = userBasicInfoService.queryUserBasic(userLoginResponse.getUserId());
            if(StringTool.isNotEmpty(userBasic)){
                if(StringTool.isNotEmpty(userBasic.getUnionid())){
                    Integer integer = userBasicInfoService.unbindweixin(userBasic.getUserId());
                    if (integer==1){
                        unbind.setMessage("success");
                        return new ResponseEntity<UnbindResponse>(unbind, HttpStatus.OK);
                    }
                }else {
                    throw new BaseException(UserInterfaceErrorCodeEnum.USER_NO_BIND_WX, "用户未绑定微信");
                }
            }else {
                throw new BaseException(UserInterfaceErrorCodeEnum.QUERY_USER_BASIC_ERROR, "用户不存在");
            }
        }else {
            throw new BaseException(UserInterfaceErrorCodeEnum.USER_NO_LOGIN, "用户未登陆");
        }
        unbind.setMessage("fail");
        return new ResponseEntity<UnbindResponse>(unbind, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UserLoginResponse> weixinLogin(@ApiParam(value = "微信唯一标识", required = true) @Valid @RequestBody WXUserLoginRequest wxUserLoginRequest){
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        UserBasicDo userBasicDo = userBasicInfoService.weixinLogin(wxUserLoginRequest.getUnionid(),wxUserLoginRequest.getType());
        if (StringTool.isNotEmpty(userBasicDo.getUserId())){
            BeanUtils.copyProperties(userBasicDo,userLoginResponse);
            if (null == userLoginResponse.getIsWxBind()){
                userLoginResponse.setIsWxBind(false);
            }
            // 设置登录会员的cookie信息
            StringBuilder sb = new StringBuilder();
            String userJson = JSON.toJSONString(userLoginResponse);
            sb.append(userJson).append(RedisNameUtil.separativeSign);
            //用户信息加密
            String str = Com35Aes.encrypt(Com35Aes.KEYCODE, sb.toString());
            CookieUtils.setCookie(request, response, CookieUtils.COOKIE_NAME_USER, str);
            // 将登录用户放入缓存（此处缓存的数据及数据结构值得推敲，暂时先全部缓存起来）
            redis.set2(RedisObjectType.SYS_USER_MANAGER.getPrefix() + Constant.SYS_FLAGS
                    + String.valueOf(userBasicDo.getPhone()), userJson, RedisObjectType.SYS_USER_MANAGER.getExpiredTime());
            return new ResponseEntity<UserLoginResponse>(userLoginResponse, HttpStatus.OK);
        }else {
            throw new BaseException(UserInterfaceErrorCodeEnum.USER_NO_BIND_WX, "用户未绑定微信");
        }

    }

    @Override
    public ResponseEntity<WXUserBasicResponse> getWXUserBasic(@ApiParam(value = "code",required = true) @Valid @RequestParam(value = "code") String code){
        WXUserBasicResponse wxUserBasicResponse = new WXUserBasicResponse();
        WXUserBasicDo wxUserBasicDo = userBasicInfoService.queryWXUserBasic(code, request, response);
        BeanUtils.copyProperties(wxUserBasicResponse,wxUserBasicDo);
        return new ResponseEntity<WXUserBasicResponse>(wxUserBasicResponse, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<UserLoginResponse> smallProgramLogin(@ApiParam(value = "临时code", required = true) @Valid @RequestParam(value = "code") String code	,
                                                                        @ApiParam(value = "偏移量", required = true) @Valid @RequestParam(value = "iv") String iv,
                                                                        @ApiParam(value = "密文", required = true) @Valid @RequestParam(value = "rawData") String rawData) {
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        UserBasicDo userBasicDo = null;
        try {
            userBasicDo = userBasicInfoService.smallProgramLogin(code,iv,rawData);

            BeanUtils.copyProperties(userBasicDo,userLoginResponse);
            if (null!=userBasicDo.getUserId()&&userBasicDo.getUserId().length()>0){
                // 设置登录会员的cookie信息
                StringBuilder sb = new StringBuilder();
                String userJson = JSON.toJSONString(userLoginResponse);
                sb.append(userJson).append(RedisNameUtil.separativeSign);
                //用户信息加密
                String str = Com35Aes.encrypt(Com35Aes.KEYCODE, sb.toString());
                CookieUtils.setCookie(request, response, CookieUtils.COOKIE_NAME_USER, str);
                // 将登录用户放入缓存（此处缓存的数据及数据结构值得推敲，暂时先全部缓存起来）
                redis.set2(RedisObjectType.SYS_USER_MANAGER.getPrefix() + Constant.SYS_FLAGS
                        + String.valueOf(userBasicDo.getPhone()), userJson, RedisObjectType.SYS_USER_MANAGER.getExpiredTime());
            }
            return new ResponseEntity<UserLoginResponse>(userLoginResponse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<UserLoginResponse>(userLoginResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
