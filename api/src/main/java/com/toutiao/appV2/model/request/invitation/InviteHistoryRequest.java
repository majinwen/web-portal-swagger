package com.toutiao.appV2.model.request.invitation;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class InviteHistoryRequest {

    @ApiParam(value = "邀请码", required = true)
    @NotNull(message = "邀请码不能为空")
    private Integer invitationCode;

    @ApiParam(value = "用户Id")
    private String userId;

    @ApiParam(value = "设备号", required = true)
    @NotEmpty(message = "设备号不能为空")
    private String equipmentNo;

}
