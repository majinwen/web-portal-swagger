package com.toutiao.app.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.qiniu.storage.model.DefaultPutRet;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.domain.user.UserLoginDomain;
import com.toutiao.app.domain.user.WXUserBasicDo;
import com.toutiao.app.service.sys.IMService;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.web.common.constant.syserror.RestfulInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.UserInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.restmodel.InvokeResult;
import com.toutiao.web.common.util.*;
import com.toutiao.web.dao.entity.admin.UserSubscribeEtc;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.mapper.officeweb.user.UserBasicMapper;
import io.rong.models.Result;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserBasicInfoServiceImpl implements UserBasicInfoService{

    @Autowired
    private UserBasicMapper userBasicMapper;
    @Autowired
    private IMService imService;
    @Autowired
    private RedisSessionUtils redis;
    private CookieUtils cookieUtils;
    @Value("${qiniu.headpic_directory}")
    public String headPicDirectory;
    @Value("${qiniu.img_wapapp_domain}")
    public String headPicPath;
    @Value("${bdw.weixin.appid}")
    public String appid;
    @Value("${bdw.weixin.secret}")
    public String secret;

    private final String weixinAccessToken = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private final String weixinUserInfo = "https://api.weixin.qq.com/sns/userinfo";

    /**
     * 更新用户头像
     * @param userId
     * @param file
     * @return
     */
    @Override
    public UserBasicDo updateUserAvatar(String userId, MultipartFile file,HttpServletRequest request, HttpServletResponse response) {

        InvokeResult invokeResult = UploadUtil.uploadImages(file);
        UserBasic userBasic = new UserBasic();
        UserBasicDo userBasicDo = new UserBasicDo();
        if(!invokeResult.getCode().equals(RestfulInterfaceErrorCodeEnum.IMAGE_SIZE_BEYOND_LIMIT.getValue())){
            if (null != ((DefaultPutRet) invokeResult.getData()).key) {

                userBasic.setAvatar(((DefaultPutRet) invokeResult.getData()).key);
                userBasic.setUserId(userId);
                int res = userBasicMapper.updateByPrimaryKeySelective(userBasic);
                if(res == 1){
                    userBasic = userBasicMapper.selectByPrimaryKey(userId);
                    //把更新的图片上传到融云
                    Result rcToken = imService.refreshRongCloudByUser(userBasic.getUserOnlySign(),userBasic.getPhone(),headPicPath+"/"+userBasic.getAvatar());
                    if(rcToken==null && rcToken.getCode()!=200){
                        throw new BaseException(RestfulInterfaceErrorCodeEnum.UPDATE_USER_AVATAR_RONGCLOUD_ERROR,"RongCloud更新用户头像异常");
                    }
                    //更新cookie中的user信息
                    UserLoginDomain userLoginDomain = new UserLoginDomain();
                    BeanUtils.copyProperties(userBasic,userLoginDomain);
                    try {
                        setCookieAndCache(userBasic.getPhone(),userLoginDomain,request,response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else{
                    throw new BaseException(UserInterfaceErrorCodeEnum.UPDATE_USER_AVATAR_ERROR,"更新用户头像信息失败");
                }
            }
            BeanUtils.copyProperties(userBasic,userBasicDo);
        }else{
            throw new BaseException(RestfulInterfaceErrorCodeEnum.IMAGE_SIZE_BEYOND_LIMIT,"图片大小超出限制");
        }
        userBasicDo.setAvatar(headPicPath + "/" + userBasicDo.getAvatar());
        return userBasicDo;
    }

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    @Override
    public UserBasicDo queryUserBasic(String userId) {

        UserBasic userBasic = userBasicMapper.selectByPrimaryKey(userId);
        UserBasicDo userBasicDo = new UserBasicDo();
        if(StringTool.isBlank(userBasic)){
            throw new BaseException(UserInterfaceErrorCodeEnum.QUERY_USER_BASIC_ERROR,"用户不存在");
        }
        BeanUtils.copyProperties(userBasic,userBasicDo);
        return userBasicDo;
    }

    /**
     * 根据用户电话查询用户信息
     * @param phone
     * @return
     */
    @Override
    public UserBasicDo queryUserBasicByPhone(String phone) {
        UserBasic userBasic = new UserBasic();
        UserBasicDo userBasicDo = new UserBasicDo();
        userBasic.setPhone(phone);
        userBasic = userBasicMapper.selectUserByPhone(userBasic);
        if(null!=userBasic){
            BeanUtils.copyProperties(userBasic,userBasicDo);
        }else{
            userBasicDo = null;
        }

        return userBasicDo;
    }

    @Override
    public int addUserBasic(UserBasic userBasic) {

        return  userBasicMapper.insertSelective(userBasic);
    }


    /**
     * 根据rcId获取用户信息
     * @param rcId
     * @return
     */
    @Override
    public UserBasicDo queryUserBasicByRcId(String rcId) {


        UserBasic userBasic = new UserBasic();
        UserBasicDo userBasicDo = new UserBasicDo();
        userBasic.setUserOnlySign(rcId);
        userBasic = userBasicMapper.selectUserBasicByRcId(userBasic);
        if(null!=userBasic){
            BeanUtils.copyProperties(userBasic,userBasicDo);
        }else{
            userBasicDo = null;
        }
        return userBasicDo;
    }

    @Override
    public UserSubscribeEtc getUserFavoriteEtcCount(Integer userId) {
        UserSubscribeEtc userSubscribeEtc = new UserSubscribeEtc();
        Integer favoriteCount = userBasicMapper.getUserFavoriteCount(userId);
        Integer subscribeCount = userBasicMapper.getUserSubscribeCount(userId);
        userSubscribeEtc.setUserFavoriteCount(favoriteCount);
        userSubscribeEtc.setUserSubscribeCount(subscribeCount);
        return userSubscribeEtc;
    }

    @Override
    public Integer unbindweixin(String userId) {
        Integer integer = userBasicMapper.unbindweixin(userId);
        return integer;
    }

    @Override
    public WXUserBasicDo queryWXUserBasic(String code, String type, HttpServletRequest request, HttpServletResponse response) {
        WXUserBasicDo wxUserBasicDo = new WXUserBasicDo();
        Map map = new HashMap();
        String weiXinInfo = "";
        //根据code获取token
        CloseableHttpClient httpclient = HttpClients.createDefault();
        List<NameValuePair> tokenFormParams = new ArrayList<>();
        tokenFormParams.add(new BasicNameValuePair("appid",appid));
        tokenFormParams.add(new BasicNameValuePair("secret",secret));
        tokenFormParams.add(new BasicNameValuePair("code",code));
        tokenFormParams.add(new BasicNameValuePair("grant_type","authorization_code"));
        UrlEncodedFormEntity tokenEntity = new UrlEncodedFormEntity(tokenFormParams, Consts.UTF_8);
        HttpPost tokenHttpPost = new HttpPost(weixinAccessToken);
        tokenHttpPost.setEntity(tokenEntity);
        CloseableHttpResponse tokenResponse = null;
        try {
            tokenResponse = httpclient.execute(tokenHttpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity tokenResult = tokenResponse.getEntity();


        //根据token获取userInfo
        CloseableHttpResponse userResponse = null;
        HttpEntity userResult = null;
        List<NameValuePair> userFormParams = new ArrayList<>();
        //todo
        userFormParams.add(new BasicNameValuePair("access_token","access_token"));
        userFormParams.add(new BasicNameValuePair("openid","openid"));
        UrlEncodedFormEntity userEntity = new UrlEncodedFormEntity(userFormParams, Consts.UTF_8);
        HttpPost userHttpPost = new HttpPost(weixinUserInfo);
        userHttpPost.setEntity(userEntity);
        try {
            userResponse = httpclient.execute(userHttpPost);
            userResult = userResponse.getEntity();
            //todo
            weiXinInfo = userResult.toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                httpclient.close();
                tokenResponse.close();
                userResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        weiXinInfo
        return wxUserBasicDo;
    }

    @Override
    public UserBasicDo weixinLogin(String unionid) {
        UserBasicDo userBasicDo = new UserBasicDo();
        UserBasic userBasic = userBasicMapper.getUserBasicByunionId(unionid);
        if (StringTool.isNotEmpty(userBasic)){
            BeanUtils.copyProperties(userBasic,userBasicDo);
            if (userBasicDo.getAvatar().indexOf("http:")==-1){
                userBasicDo.setAvatar(headPicPath + "/" + userBasicDo.getAvatar());
            }
        }
        return userBasicDo;
    }


    private void setCookieAndCache(String phone, UserLoginDomain userLoginDomain,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置登录会员的cookie信息
        StringBuilder sb = new StringBuilder();
        String userJson = JSON.toJSONString(userLoginDomain);
        sb.append(userJson).append(RedisNameUtil.separativeSign);
        //用户信息加密
        String str = Com35Aes.encrypt(Com35Aes.KEYCODE, sb.toString());
        cookieUtils.setCookie(request, response, CookieUtils.COOKIE_NAME_USER, str);
        // 将登录用户放入缓存（此处缓存的数据及数据结构值得推敲，暂时先全部缓存起来）
        redis.set2(RedisObjectType.SYS_USER_MANAGER.getPrefix() + Constant.SYS_FLAGS
                + phone, userJson, RedisObjectType.SYS_USER_MANAGER.getExpiredTime());
    }
}
