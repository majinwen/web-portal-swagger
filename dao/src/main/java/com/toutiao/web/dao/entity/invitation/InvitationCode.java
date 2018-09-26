package com.toutiao.web.dao.entity.invitation;

import lombok.Data;

import java.util.Date;

@Data
public class InvitationCode {
    /**
     * Id
     */
    private Integer id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 邀请码
     */
    private Integer code;

    /**
     * 邀请次数
     */
    private Integer inviteTotal;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 用户手机号
     */
    private String phone;
}