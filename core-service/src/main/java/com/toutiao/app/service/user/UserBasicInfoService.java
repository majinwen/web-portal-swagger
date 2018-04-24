package com.toutiao.app.service.user;

import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import org.springframework.web.multipart.MultipartFile;



public interface UserBasicInfoService {


    /**
     * 更新用户基本信息
     * @param userId
     * @param multipartFile
     * @return
     */
    UserBasicDo updateUserAvatar(String userId, MultipartFile multipartFile);

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
}
