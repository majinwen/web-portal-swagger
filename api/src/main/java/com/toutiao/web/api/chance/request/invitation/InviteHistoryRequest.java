package com.toutiao.web.api.chance.request.invitation;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class InviteHistoryRequest {
    /**
     * 邀请码
     */
    @NotNull(message = "邀请码不能为空")
    private Integer invitationCode;

    /**
     * 用户Id
     */
//    @NotEmpty(message = "用户Id不能为空")
    private String userId;

    /**
     * 设备号
     */
    @NotEmpty(message = "设备号不能为空")
    private String equipmentNo;
}
