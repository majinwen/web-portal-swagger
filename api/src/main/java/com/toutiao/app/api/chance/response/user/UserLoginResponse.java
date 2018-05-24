package com.toutiao.app.api.chance.response.user;

import lombok.Data;


@Data
public class UserLoginResponse {



    /**
     * 用户名
     */
    private String userName;



    /**
     * 融云token标识
     */
    private String rongCloudToken;


    /**
     * 用户唯一标志
     */
    private String userOnlySign;

    /**
     *
     * 用户头像
     */
    private String avatar;

    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户昵称
     */
//    private String userNickName;

}
