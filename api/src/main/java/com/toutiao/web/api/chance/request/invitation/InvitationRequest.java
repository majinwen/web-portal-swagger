package com.toutiao.web.api.chance.request.invitation;

import lombok.Data;

@Data
public class InvitationRequest {

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 设备号
     */
    private String equipmentNo;
}
