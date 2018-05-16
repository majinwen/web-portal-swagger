package com.toutiao.app.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.qiniu.storage.model.DefaultPutRet;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.domain.user.UserLoginDomain;
import com.toutiao.app.service.sys.IMService;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.web.common.constant.syserror.RestfulInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.UserInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.restmodel.InvokeResult;
import com.toutiao.web.common.util.*;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.mapper.officeweb.user.UserBasicMapper;
import io.rong.models.Result;
import io.rong.models.response.TokenResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        if (null != ((DefaultPutRet) invokeResult.getData()).key) {

            userBasic.setAvatar(((DefaultPutRet) invokeResult.getData()).key);
            userBasic.setUserId(userId);
            int res = userBasicMapper.updateByPrimaryKeySelective(userBasic);
            if(res == 1){
                userBasic = userBasicMapper.selectByPrimaryKey(userId);
                //把更新的图片上传到融云
                Result rcToken = imService.refreshRongCloudByUser(userBasic.getUserOnlySign(),userBasic.getUserName(),headPicPath+"/"+userBasic.getAvatar());
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
