package com.toutiao.web.dao.entity.officeweb.user;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

@Data
public class UserBasic {
    /**
     * ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPwd;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户信息状态 1-有效，0-无效
     */
    private Short userStatus;

    /**
     * 1-WAP，2-APP
     */
    private Short registerSource;

    /**
     * 登录类型 1-手机号，2-邮箱，3-用户名，4-微信
     */
    private Short identityType;

    /**
     * 登录类型标识 e.g.(注册填入的 手机号码，邮箱，用户名称等)
     */
    private String identifier;

    /**
     * 融云token标识
     */
    private String rongCloudToken;

    /**
     * 用户唯一标志
     */
    private String userOnlySign;


    public static UserBasic getCurrent() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String userJson = (String) request.getAttribute("userLogin");
        UserBasic user = new UserBasic();
        if (userJson.equals("请登录")) {
            user = null;
        } else {
            user = JSON.parseObject(userJson, UserBasic.class);
        }

        return user;
    }

    public static boolean IsLogin() {
        return Objects.nonNull(UserBasic.getCurrent());
    }

}