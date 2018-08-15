package com.toutiao.web.api.chance.response.invitation;

import com.toutiao.app.domain.invitation.InviteHistoryDo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InvitationResponse {
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
     * 邀请记录
     */
    private List<InviteHistoryDo> invateHistoryDos;
}
