package com.toutiao.web.dao.entity.invitation;

import lombok.Data;

import java.util.Date;

@Data
public class InviteHistory {
    /**
     * Id
     */
    private Integer id;

    /**
     * 邀请码
     */
    private Integer invitationCode;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否有效
     */
    private Short isValid;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 设备号
     */
    private String equipmentNo;

    /**
     * 用户手机号
     */
    private String phone;
}