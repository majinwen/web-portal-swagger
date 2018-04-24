package com.toutiao.app.service.user.impl;

import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.service.sys.IMService;
import com.toutiao.app.service.user.UserLoginService;
import com.toutiao.web.common.constant.syserror.ShortMessageInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.UserInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.*;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.mapper.officeweb.user.UserBasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    private Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

    @Autowired
    private RedisSessionUtils redis;
    @Autowired
    private UserBasicMapper userBasicMapper;
    @Autowired
    private IMService imService;


    @Override
    public UserBasicDo checkUserVerifyCodeLogin(UserBasicDo userBasicDo,HttpServletRequest request, HttpServletResponse response) {

        //获取redis缓存中的手机号验证码码判断是否有效
        String tempVerifyCode = redis.getValue(ServiceStateConstant.ALIYUN_SHORT_MESSAGE_LOGIN_REGISTER +"_"+ userBasicDo.getUserName());
        UserBasicDo ubd = new UserBasicDo();
        UserBasic userBasic = new UserBasic();


            if(tempVerifyCode!="" && userBasicDo.getVerifyCode().equals(tempVerifyCode)) {
                try {
                    //从cookie中获取图片验证码与页面传递过来的验证码进行对比
                    if (StringTool.getInteger(redis.getValue(userBasicDo.getUserName() + RedisNameUtil.separativeSignCount)) > Constant.LOGIN_ERROR_TIMES) {

                        if (StringTool.isNotBlank(userBasicDo.getImageCode()) && StringTool.isNotBlank(CookieUtils.getCookie(request, response,
                                "imageCode")) && !CookieUtils.getCookie(request, response,
                                "imageCode").equalsIgnoreCase(userBasicDo.getImageCode())) {
                            throw new BaseException(ShortMessageInterfaceErrorCodeEnum.IMAGE_CODE_MESSAGE_ERROR, "图形验证码错误！");
                        }

                    }


                    //验证成功,判断用户是否存在，如果存在则，则更新用户登录时间

                    userBasic.setUserName(userBasicDo.getUserName());
                    userBasic.setIdentityType(userBasicDo.getIdentityType());
                    userBasic.setIdentifier(userBasicDo.getUserName());
                    userBasic.setUserStatus(ServiceStateConstant.USER_BASIC_STATUS);

                    userBasic = userBasicMapper.selectUserByExample(userBasic);
                    if (StringTool.isNotBlank(userBasic)) {
                        //验证成功后，清除缓存
                        redis.delKey(ServiceStateConstant.ALIYUN_SHORT_MESSAGE_LOGIN_REGISTER + userBasicDo.getUserName());
                        BeanUtils.copyProperties(userBasic, ubd);
                    } else {
                        //注册新用户
                        UserBasic insertUserBasic = new UserBasic();
                        Date date = new Date();
                        insertUserBasic.setUserName(userBasicDo.getUserName());

                        String[] userAvatar = ServiceStateConstant.SYS_USER_AVATAR;
                        int avatarNum = new Random().nextInt(ServiceStateConstant.RANDOM_AVATAR);
                        insertUserBasic.setAvatar(userAvatar[avatarNum]);
                        insertUserBasic.setCreateTime(date);
                        insertUserBasic.setLoginTime(date);
                        insertUserBasic.setPhone(userBasicDo.getUserName());
                        insertUserBasic.setUpdateTime(date);
                        insertUserBasic.setUserStatus(ServiceStateConstant.USER_BASIC_STATUS);
                        insertUserBasic.setRegisterSource(ServiceStateConstant.USER_REGISTER_SOURCE_APP);
                        insertUserBasic.setIdentityType(userBasicDo.getIdentityType());
                        insertUserBasic.setIdentifier(userBasicDo.getUserName());

                        insertUserBasic.setUserOnlySign(UUID.randomUUID().toString().replace("-", ""));
                        //用户注册融云信息
                        String rcToken = imService.queryRongCloudTokenByUser(insertUserBasic.getUserOnlySign(), userBasicDo.getUserName(), insertUserBasic.getAvatar());
                        insertUserBasic.setRongCloudToken(rcToken);

                        int userId = userBasicMapper.insertSelective(insertUserBasic);
                        BeanUtils.copyProperties(insertUserBasic, ubd);
                    }

                } catch (Exception e) {
                    throw new BaseException(UserInterfaceErrorCodeEnum.USER_LOGIN_EXCEPTION, "用户登录异常");
                }
            }else{
                throw new BaseException(ShortMessageInterfaceErrorCodeEnum.SHORT_MESSAGE_ERROR,"短信验证码错误！");
            }


        return ubd;
    }

}
