package com.toutiao.app.domain.invitation;

import lombok.Data;

@Data
public class InvitationCodeDoQuery {
    /**
     * 用户Id
     */
    private String userId;

    /**
     * 设备号
     */
    private String equipmentNo;
}
