package com.toutiao.app.domain.user;

import lombok.Data;

@Data
public class WXUserBasicDo {
    /**
     * 用户名称
     */
    private String nickname;

    /**
     * 性别,1:男性,2:女性
     */
    private String gender;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    /**
     * 用户头像
     */
    private String headimgurl;
}
