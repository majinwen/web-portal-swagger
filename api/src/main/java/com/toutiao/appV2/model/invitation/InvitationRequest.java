package com.toutiao.appV2.model.invitation;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class InvitationRequest {

    @ApiParam(value = "用户Id")
    private String userId;

    @ApiParam(value = "设备号")
    private String equipmentNo;

}
