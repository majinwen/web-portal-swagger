package com.toutiao.app.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.storage.model.DefaultPutRet;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.domain.user.UserLoginDomain;
import com.toutiao.app.domain.user.WXUserBasicDo;
import com.toutiao.app.service.sys.IMService;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.web.common.constant.syserror.RestfulInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.UserInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.httpUtil.HttpUtils;
import com.toutiao.web.common.restmodel.InvokeResult;
import com.toutiao.web.common.util.*;
import com.toutiao.web.dao.entity.admin.UserSubscribeEtc;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.mapper.officeweb.user.UserBasicMapper;
import io.rong.models.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
    @Value("${bdw.cms.base.url}")
    private String cmsBaseUrl;
    @Value("${bdw.cms.news.count}")
    private String newsCountUrl;
    @Value("${bdw.weixinSmallProgram.appid}")
    public String smallProgramAppId;
    @Value("${bdw.weixinSmallProgram.secret}")
    public String smallProgramSecret;
    @Value("${bdw.weixinWeb.appid}")
    public String webAppId;
    @Value("${bdw.weixinWeb.secret}")
    public String webSecret;

    private final String weixinWebAccessToken = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private final String weixinsmallProgramAccessToken = "https://api.weixin.qq.com/sns/jscode2session";
    private final String weixinWebUserInfo = "https://api.weixin.qq.com/sns/userinfo";

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
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("userId", userId.toString());
        String result = HttpUtils.get(cmsBaseUrl + newsCountUrl, paramMap);

        if (StringUtils.isNotEmpty(result)) {
            Map resultMap = JSON.parseObject(result, Map.class);
            Object newsCount = resultMap.get("newsCount");
            Object channelCount = resultMap.get("channelCount");

            if (null != newsCount) {
                favoriteCount += (Integer) newsCount;
            }

            if (null != channelCount) {
                subscribeCount += (Integer) channelCount;
            }
        }
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
    public WXUserBasicDo queryWXUserBasic(String code, HttpServletRequest request, HttpServletResponse response) {
        WXUserBasicDo wxUserBasicDo = new WXUserBasicDo();
        String tokenInfo = "";
        String userInfo = "";
        //根据code获取token
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String tokenUrl = weixinWebAccessToken+"?appid="+webAppId+"&secret="+webSecret+"&code="+code+"&grant_type=authorization_code";
        HttpGet tokenHttpGet = new HttpGet(tokenUrl);
        CloseableHttpResponse tokenResponse = null;
        try {
            tokenResponse = httpclient.execute(tokenHttpGet);
            HttpEntity tokenResult = tokenResponse.getEntity();
            tokenInfo =EntityUtils.toString(tokenResult);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String access_token= String.valueOf(JSON.parseObject(tokenInfo).get("access_token"));
        String openid = String.valueOf(JSON.parseObject(tokenInfo).get("openid"));


        //根据token获取userInfo
        CloseableHttpResponse userResponse = null;
        HttpEntity userResult = null;
        String userInfoUrl = weixinWebUserInfo+"?access_token="+access_token+"&openid="+openid;
        HttpGet userHttpGet = new HttpGet(userInfoUrl);
        try {
            userResponse = httpclient.execute(userHttpGet);
            userResult = userResponse.getEntity();
            userInfo = EntityUtils.toString(userResult);
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
    public UserBasicDo weixinLogin(String unionid, String type) {
        UserBasicDo userBasicDo = new UserBasicDo();

        //解密
        if("2".equals(type)||"3".equals(type)){
            String decrypt = Com35Aes.decrypt(Com35Aes.KEYCODE, unionid);
            String[] split = decrypt.split(RedisNameUtil.separativeSign);
            if (split.length==2){
                unionid = split[0];
            }
        }

        UserBasic userBasic = userBasicMapper.getUserBasicByunionId(unionid);
        if (StringTool.isNotEmpty(userBasic)){
            BeanUtils.copyProperties(userBasic,userBasicDo);
            if (userBasicDo.getAvatar().indexOf("http:")==-1){
                userBasicDo.setAvatar(headPicPath + "/" + userBasicDo.getAvatar());
            }
            //unionid加密
            String str = Com35Aes.encrypt(Com35Aes.KEYCODE, (userBasic.getUnionid()+RedisNameUtil.separativeSign+"加密").toString());
            userBasicDo.setUnionid(str);
            userBasicDo.setIsWxBind(true);
        }
        return userBasicDo;
    }

    @Override
    public Map getSmallProgramInfo(String code) {
        Map map = new HashMap();
        String tokenInfo = "";
        //根据code获取token
        CloseableHttpClient httpclient = HttpClients.createDefault();
        List<NameValuePair> tokenFormParams = new ArrayList<>();
        String tokenUrl = weixinsmallProgramAccessToken+"?appid="+smallProgramAppId+"&secret="+smallProgramSecret+"&js_code="+code+"&grant_type=authorization_code";
        HttpGet tokenHttpGet = new HttpGet(tokenUrl);
        CloseableHttpResponse tokenResponse = null;
        try {
            tokenResponse = httpclient.execute(tokenHttpGet);
            HttpEntity tokenResult = tokenResponse.getEntity();
            tokenInfo =EntityUtils.toString(tokenResult);
            String sessionKey= String.valueOf(JSON.parseObject(tokenInfo).get("session_key"));
            String openid = String.valueOf(JSON.parseObject(tokenInfo).get("openid"));
//            if (StringUtils.isEmpty(JSON.parseObject(tokenInfo).get("errmsg").toString())){
//                throw new BaseException(Integer.valueOf(String.valueOf(JSON.parseObject(tokenInfo).get("errcode"))),String.valueOf(JSON.parseObject(tokenInfo).get("errmsg")));
//            }
            map.put("openid",openid);
            map.put("sessionKey",sessionKey);
            httpclient.close();
            tokenResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public UserBasicDo smallProgramLogin(String code, String iv, String rawData) throws Exception{
        Map smallProgramInfo = getSmallProgramInfo(code);
        UserBasicDo userBasicDo = new UserBasicDo();
        //解密
        String result  = AesCbcUtil.decrypt(rawData, String.valueOf(smallProgramInfo.get("sessionKey")), iv, "UTF-8");
        if (null != result && result.length() > 0) {
            JSONObject userInfoJSON = JSON.parseObject(result);
            String unionId = String.valueOf(userInfoJSON.get("unionId"));
            userBasicDo = weixinLogin(unionId,"0");
            //unionid加密
            String str = Com35Aes.encrypt(Com35Aes.KEYCODE, (unionId+RedisNameUtil.separativeSign+"加密").toString());
            userBasicDo.setUnionid(str);
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
