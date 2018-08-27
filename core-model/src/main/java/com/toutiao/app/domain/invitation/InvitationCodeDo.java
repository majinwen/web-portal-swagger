package com.toutiao.app.domain.invitation;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InvitationCodeDo {
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

    /**
     * 邀请记录
     */
    private List<InviteHistoryDo> invateHistoryDos;
}
