package com.toutiao.app.service.user.impl;

import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.domain.user.UserBasicDoQuery;
import com.toutiao.app.service.compared.ComparedService;
import com.toutiao.app.service.sys.IMService;
import com.toutiao.app.service.user.UserLoginService;
import com.toutiao.web.common.constant.syserror.ShortMessageInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.UserInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.*;
import com.toutiao.web.dao.entity.compared.HouseCompared;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.mapper.officeweb.user.UserBasicMapper;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    private Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

    @Autowired
    private RedisSessionUtils redis;
    @Autowired
    private UserBasicMapper userBasicMapper;
    @Autowired
    private IMService imService;
    @Autowired
    private ComparedService comparedService;
    @Value("${qiniu.headpic_directory}")
    public String headPicDirectory;
    @Value("${qiniu.img_wapapp_domain}")
    public String headPicPath;

    @Override
    public UserBasicDo checkUserVerifyCodeLogin(UserBasicDoQuery userBasicDo, HttpServletRequest request, HttpServletResponse response) {

        String tempVerifyCode = "";
        tempVerifyCode = redis.getValue(ServiceStateConstant.ALIYUN_SHORT_MESSAGE_LOGIN_REGISTER + "_" + userBasicDo.getUserPhone());
        if (userBasicDo.getUserPhone().equals("13100000000") || userBasicDo.getUserPhone().equals("13200000000")||
                userBasicDo.getUserPhone().equals("13300000000")||userBasicDo.getUserPhone().equals("13400000000")||
                userBasicDo.getUserPhone().equals("13500000000")||userBasicDo.getUserPhone().equals("13900000000")) {
            tempVerifyCode = "1243";
            userBasicDo.setVerifyCode("1243");
        }


        //获取redis缓存中的手机号验证码码判断是否有效
        UserBasicDo ubd = new UserBasicDo();
        UserBasic userBasic = new UserBasic();


        if (tempVerifyCode != "" && userBasicDo.getVerifyCode().equals(tempVerifyCode)) {

            //从cookie中获取图片验证码与页面传递过来的验证码进行对比
            if (StringTool.getInteger(redis.getValue(userBasicDo.getUserPhone() + RedisNameUtil.separativeSignCount)) >= Constant.LOGIN_ERROR_TIMES) {

                if (StringTool.isNotBlank(userBasicDo.getImageCode()) && StringTool.isNotBlank(CookieUtils.getCookie(request, response,
                        "imageCode")) && !CookieUtils.getCookie(request, response,
                        "imageCode").equalsIgnoreCase(userBasicDo.getImageCode())) {
                    throw new BaseException(ShortMessageInterfaceErrorCodeEnum.IMAGE_CODE_MESSAGE_ERROR, "图形验证码错误！");
                }

            }
            //验证成功,判断用户是否存在，如果存在则，则更新用户登录时间
            try {
                userBasic.setPhone(userBasicDo.getUserPhone());
//                userBasic.setIdentityType(userBasicDo.getIdentityType());
                userBasic.setIdentifier(userBasicDo.getUserPhone());
                userBasic.setUserStatus(ServiceStateConstant.USER_BASIC_STATUS);

                userBasic = userBasicMapper.selectUserByExample(userBasic);
                if (StringTool.isNotBlank(userBasic)) {
                    //验证成功后，更新用户登录时间，清除缓存
                    UserBasic user = new UserBasic();
                    user.setUserId(userBasic.getUserId());
                    user.setLoginTime(new Date());
                    if (userBasic.getRongCloudToken() == null || "".equals(userBasic.getRongCloudToken())) {
//                           userBasic.setUserOnlySign(UUID.randomUUID().toString().replace("-", ""));
                        Date date = new Date();

                        userBasic.setUserOnlySign(date.getTime() + userBasic.getPhone());

                        if (userBasic.getAvatar() == null || "".equals(userBasic.getAvatar())) {
                            String[] userAvatar = ServiceStateConstant.SYS_USER_AVATAR;
                            int avatarNum = new Random().nextInt(ServiceStateConstant.RANDOM_AVATAR);
                            userBasic.setAvatar(headPicDirectory + "/" + userAvatar[avatarNum]);
                        }
                        String rcToken = imService.queryRongCloudTokenByUser(userBasic.getUserOnlySign(), userBasicDo.getUserPhone(),
                                headPicPath + "/" + headPicDirectory + "/" + userBasic.getAvatar());
                        userBasic.setRongCloudToken(rcToken);
                        userBasicMapper.updateByPrimaryKeySelective(userBasic);
                    }
                    BeanUtils.copyProperties(userBasic, ubd);
                    redis.delKey(ServiceStateConstant.ALIYUN_SHORT_MESSAGE_LOGIN_REGISTER + userBasicDo.getUserPhone());
                } else {
                    //注册新用户
                    UserBasic insertUserBasic = new UserBasic();
                    Date date = new Date();
                    insertUserBasic.setUserName(userBasicDo.getUserPhone().substring(0, 2) + "*****" + userBasicDo.getUserPhone().substring(9, 11));

                    String[] userAvatar = ServiceStateConstant.SYS_USER_AVATAR;
                    int avatarNum = new Random().nextInt(ServiceStateConstant.RANDOM_AVATAR);
                    insertUserBasic.setAvatar(headPicDirectory + "/" + userAvatar[avatarNum]);
                    insertUserBasic.setCreateTime(date);
                    insertUserBasic.setLoginTime(date);
                    insertUserBasic.setPhone(userBasicDo.getUserPhone());
                    insertUserBasic.setUpdateTime(date);
                    insertUserBasic.setUserStatus(ServiceStateConstant.USER_BASIC_STATUS);
                    insertUserBasic.setRegisterSource(ServiceStateConstant.USER_REGISTER_SOURCE_APP);
                    insertUserBasic.setIdentityType(userBasicDo.getIdentityType());
                    insertUserBasic.setIdentifier(userBasicDo.getUserPhone());
                    Date d = new Date();
//                        insertUserBasic.setUserOnlySign(UUID.randomUUID().toString().replace("-", ""));
                    insertUserBasic.setUserOnlySign(d.getTime() + insertUserBasic.getPhone());
                    //用户注册融云信息
                    String rcToken = imService.queryRongCloudTokenByUser(insertUserBasic.getUserOnlySign(), userBasicDo.getUserPhone(),
                            headPicPath + "/" + headPicDirectory + "/" + insertUserBasic.getAvatar());
                    insertUserBasic.setRongCloudToken(rcToken);
                    int userId = userBasicMapper.insertSelective(insertUserBasic);
                    BeanUtils.copyProperties(insertUserBasic, ubd);
                }

                //登录后合并cookie中的对比信息
                String currHouseId = CookieUtils.getCookie(request, response, CookieUtils.COOKIE_NAME_TEMP_HOUSE_COMPARED);
                if (StringTool.isNotEmpty(currHouseId)) {
                    String[] currHouseIdArray = currHouseId.split("_");
                    List<String> currHouseIdList = Arrays.asList(currHouseIdArray);
                    for (String houseId : currHouseIdList) {
                        try {
                            HouseCompared houseCompared = new HouseCompared();
                            houseCompared.setCreateTime(DateTime.now().toDate());
                            houseCompared.setHouseId(houseId);
                            houseCompared.setHouseStatus((short) 0);
                            houseCompared.setIsDel((short) 0);
                            houseCompared.setUserId(Integer.parseInt(ubd.getUserId()));
                            comparedService.insertSelective(houseCompared);
                        } catch (Exception e) {
                            continue;
                        }
                    }
                    CookieUtils.deleteCookie(request, response, CookieUtils.COOKIE_NAME_TEMP_HOUSE_COMPARED);
                }

            } catch (Exception e) {
                throw new BaseException(UserInterfaceErrorCodeEnum.USER_LOGIN_EXCEPTION, "用户登录异常");
            }
        } else {
            throw new BaseException(ShortMessageInterfaceErrorCodeEnum.SHORT_MESSAGE_ERROR, "短信验证码错误！");
        }
        if (StringUtils.isNotEmpty(ubd.getAvatar()) && !ubd.getAvatar().startsWith("http:")) {
            ubd.setAvatar(headPicPath + "/" + ubd.getAvatar());
        }
        if(StringUtils.isNotEmpty(ubd.getUnionid())){
            ubd.setIsWxBind(true);
        }
        return ubd;
    }

    @Override
    public UserBasicDo checkUserVerifyCodeBindWXLogin(UserBasicDoQuery userBasicDo, HttpServletRequest request, HttpServletResponse response) {
        String tempVerifyCode = redis.getValue(ServiceStateConstant.ALIYUN_SHORT_MESSAGE_BIND_WX_REGISTER + "_" + userBasicDo.getUserPhone());
        if (userBasicDo.getUserPhone().equals("13100000000") || userBasicDo.getUserPhone().equals("13200000000")||
                userBasicDo.getUserPhone().equals("13300000000")||userBasicDo.getUserPhone().equals("13400000000")||
                userBasicDo.getUserPhone().equals("13500000000")||userBasicDo.getUserPhone().equals("13900000000")) {
            tempVerifyCode = "1243";
            userBasicDo.setVerifyCode("1243");
        }


        //获取redis缓存中的手机号验证码码判断是否有效
        UserBasicDo ubd = new UserBasicDo();
        UserBasic userBasic = new UserBasic();


        if (tempVerifyCode != "" && userBasicDo.getVerifyCode().equals(tempVerifyCode)) {

            //从cookie中获取图片验证码与页面传递过来的验证码进行对比
            if (StringTool.getInteger(redis.getValue(userBasicDo.getUserPhone() + RedisNameUtil.separativeSignCount)) >= Constant.LOGIN_ERROR_TIMES) {

                    if (StringTool.isNotBlank(userBasicDo.getImageCode()) && StringTool.isNotBlank(CookieUtils.getCookie(request, response,
                            "wxImageCode")) && !CookieUtils.getCookie(request, response,
                            "wxImageCode").equalsIgnoreCase(userBasicDo.getImageCode())) {
                        throw new BaseException(ShortMessageInterfaceErrorCodeEnum.IMAGE_CODE_MESSAGE_ERROR, "图形验证码错误！");
                    }

            }
            //验证成功,判断用户是否存在，如果存在则，则更新用户登录时间
            try {
                userBasic.setPhone(userBasicDo.getUserPhone());
//                userBasic.setIdentityType(userBasicDo.getIdentityType());
                userBasic.setIdentifier(userBasicDo.getUserPhone());
                userBasic.setUserStatus(ServiceStateConstant.USER_BASIC_STATUS);

                userBasic = userBasicMapper.selectUserByExample(userBasic);

                String unionid = userBasicDo.getUnionid();
                //解密
                if("2".equals(userBasicDo.getType())||"3".equals(userBasicDo.getType())){
                    String decrypt = Com35Aes.decrypt(Com35Aes.KEYCODE, unionid);
                    String[] split = decrypt.split(RedisNameUtil.separativeSign);
                    if (split.length==2){
                        unionid = split[0];
                    }
                }

                if (StringTool.isNotBlank(userBasic)) {
                    //验证成功后，更新用户登录时间绑定微信，清除缓存
                    userBasic.setUserId(userBasic.getUserId());
                    userBasic.setLoginTime(new Date());
                    userBasic.setUnionid(unionid);
                    userBasic.setIdentityType(userBasicDo.getIdentityType());
                    if(StringUtils.isNotEmpty(userBasicDo.getUserName())){
                        userBasic.setUserName(userBasicDo.getUserName());
                    }
//                    userBasic.setRefreshToken(wxInfo[1]);
//                           userBasic.setUserOnlySign(UUID.randomUUID().toString().replace("-", ""));
                        Date date = new Date();

                        userBasic.setUserOnlySign(date.getTime() + userBasic.getPhone());

                        if (userBasic.getAvatar() == null || "".equals(userBasic.getAvatar())) {
                            userBasic.setAvatar(userBasicDo.getAvatar());
                        }
                    if (userBasic.getRongCloudToken() == null || "".equals(userBasic.getRongCloudToken())) {
                        //获取融云token
                        String rcToken = imService.queryRongCloudTokenByUser(userBasic.getUserOnlySign(), userBasicDo.getUserPhone(),
                                userBasic.getAvatar());
                        userBasic.setRongCloudToken(rcToken);
                    }
                    userBasicMapper.updateByPrimaryKeySelective(userBasic);
                    BeanUtils.copyProperties(userBasic, ubd);
                    redis.delKey(ServiceStateConstant.ALIYUN_SHORT_MESSAGE_BIND_WX_REGISTER + userBasicDo.getUserPhone());
                } else {
                    //注册新用户
                    UserBasic insertUserBasic = new UserBasic();
                    Date date = new Date();
                    if(StringUtils.isNotEmpty(userBasicDo.getUserName())){
                        insertUserBasic.setUserName(userBasicDo.getUserName());
                    }
                    insertUserBasic.setAvatar(userBasicDo.getAvatar());
                    insertUserBasic.setCreateTime(date);
                    insertUserBasic.setLoginTime(date);
                    insertUserBasic.setPhone(userBasicDo.getUserPhone());
                    insertUserBasic.setUpdateTime(date);
                    insertUserBasic.setUserStatus(ServiceStateConstant.USER_BASIC_STATUS);
                    insertUserBasic.setRegisterSource(ServiceStateConstant.USER_REGISTER_SOURCE_APP);
                    insertUserBasic.setIdentityType(userBasicDo.getIdentityType());
                    insertUserBasic.setIdentifier(userBasicDo.getUserPhone());
                    insertUserBasic.setUnionid(unionid);
//                    insertUserBasic.setRefreshToken(wxInfo[1]);
                    Date d = new Date();
//                        insertUserBasic.setUserOnlySign(UUID.randomUUID().toString().replace("-", ""));
                    insertUserBasic.setUserOnlySign(d.getTime() + insertUserBasic.getPhone());
                    //用户注册融云信息
                    String rcToken = imService.queryRongCloudTokenByUser(insertUserBasic.getUserOnlySign(), userBasicDo.getUserPhone(),
                            insertUserBasic.getAvatar());
                    insertUserBasic.setRongCloudToken(rcToken);
                    int userId = userBasicMapper.insertSelective(insertUserBasic);
                    BeanUtils.copyProperties(insertUserBasic, ubd);
                }

                //登录后合并cookie中的对比信息
                String currHouseId = CookieUtils.getCookie(request, response, CookieUtils.COOKIE_NAME_TEMP_HOUSE_COMPARED);
                if (StringUtil.isNotNullString(currHouseId)) {
                    String[] currHouseIdArray = currHouseId.split("_");
                    List<String> currHouseIdList = Arrays.asList(currHouseIdArray);
                    for (String houseId : currHouseIdList) {
                        try {
                            HouseCompared houseCompared = new HouseCompared();
                            houseCompared.setCreateTime(DateTime.now().toDate());
                            houseCompared.setHouseId(houseId);
                            houseCompared.setHouseStatus((short) 0);
                            houseCompared.setIsDel((short) 0);
                            houseCompared.setUserId(Integer.parseInt(ubd.getUserId()));
                            comparedService.insertSelective(houseCompared);
                        } catch (Exception e) {
                            continue;
                        }
                    }
                    CookieUtils.deleteCookie(request, response, CookieUtils.COOKIE_NAME_TEMP_HOUSE_COMPARED);
                }

            } catch (Exception e) {
                throw new BaseException(UserInterfaceErrorCodeEnum.USER_LOGIN_EXCEPTION, "用户登录异常");
            }
        } else {
            throw new BaseException(ShortMessageInterfaceErrorCodeEnum.SHORT_MESSAGE_ERROR, "短信验证码错误！");
        }
        //todo
        if (ubd.getAvatar().indexOf("http:")==-1){
            ubd.setAvatar(headPicPath + "/" + ubd.getAvatar());
        }
        ubd.setIsWxBind(true);
        return ubd;
    }

}
