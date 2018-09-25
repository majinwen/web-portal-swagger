package com.toutiao.app.domain.invitation;

import lombok.Data;

import java.util.Date;

@Data
public class SuperInviteHistoryDo {
    /**
     * Id
     */
    private Integer id;

    /**
     * 一级邀请码
     */
    private String firstLevelCode;

    /**
     * 二级邀请码
     */
    private String secondLevelCode;

    /**
     * 三级邀请码
     */
    private String thirdLevelCode;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 设备号
     */
    private String equipmentNo;

    /**
     * 用户手机号
     */
    private String phone;

    private Short isValid;

    private Date payTime;
}
