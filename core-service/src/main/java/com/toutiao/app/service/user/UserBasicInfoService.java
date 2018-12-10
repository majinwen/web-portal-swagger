package com.toutiao.app.service.user;

import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.domain.user.WXUserBasicDo;
import com.toutiao.web.dao.entity.admin.UserSubscribeEtc;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public interface UserBasicInfoService {


    /**
     * 更新用户基本信息
     * @param userId
     * @param multipartFile
     * @return
     */
    UserBasicDo updateUserAvatar(String userId, MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response);

    /**
     * 查询用于基本信息
     * @param userId
     * @return
     */
    UserBasicDo queryUserBasic(String userId);

    /**
     * 根据用户电话查询用户信息
     * @param phone
     * @return
     */
    UserBasicDo queryUserBasicByPhone(String phone);

    /**
     * 新增用户信息
     * @param userBasic
     * @return
     */
    int addUserBasic(UserBasic userBasic);

    /**
     * 根据rcId获取用户信息
     * @param rcId
     * @return
     */
    UserBasicDo queryUserBasicByRcId(String rcId);

    UserSubscribeEtc getUserFavoriteEtcCount(Integer userId);
    /**
     * 解除微信绑定
     */
    Integer unbindweixin(String userId);

    /**
     * 获取微信信息
     */
    WXUserBasicDo queryWXUserBasic(String code, String type, HttpServletRequest request, HttpServletResponse response);

    /**
     * 微信登录
     */
    UserBasicDo weixinLogin(String unionid, String type);

    /**
     * 获取小程序信息
     * @param code
     */
    Map getSmallProgramInfo(String code);

    /**
     * 小程序获取微信信息
     * @param code
     * @param iv
     * @param rawData
     * @return
     */
    UserBasicDo smallProgramLogin(String code, String iv, String rawData);

}
