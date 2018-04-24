package com.toutiao.app.service.sys;

import io.rong.models.Result;

public interface IMService {

    /**
     * 根据 用户 Id 用户名称 用户头像 URI 获取token
     * @param userId
     * @param userName
     * @param avatar
     * @return
     */
    String queryRongCloudTokenByUser(String userId, String userName, String avatar);


    /**
     * 根据 用户 Id 用户名称 用户头像 URI 获取融云信息
     * @param userId
     * @param userName
     * @param avatar
     * @return
     */
    Result refreshRongCloudByUser(String userId, String userName, String avatar);
}
