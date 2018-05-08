package com.toutiao.app.domain.user;

import lombok.Data;


@Data
public class UserLoginDomain {



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

}
