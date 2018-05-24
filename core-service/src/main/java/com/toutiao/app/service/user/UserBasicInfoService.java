package com.toutiao.app.service.user;

import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
}
