package com.toutiao.web.api.chance.request.invitation;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class InvitationRequest {

    /**
     * 用户Id
     */
    @NotEmpty(message = "缺少用户Id")
    private String userId;

    /**
     * 设备号
     */
    private String equipmentNo;
}
