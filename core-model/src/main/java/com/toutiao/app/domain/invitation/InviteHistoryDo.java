package com.toutiao.app.domain.invitation;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;

@Data
public class InviteHistoryDo {
    @ApiParam(value = "Id")
    private Integer id;

    @ApiParam(value = "邀请码")
    private Integer invitationCode;

    @ApiParam(value = "用户Id")
    private String userId;

    @ApiParam(value = "创建时间")
    private Date createTime;

    @ApiParam(value = "是否有效")
    private Short isValid;

    @ApiParam(value = "支付时间")
    private Date payTime;

    @ApiParam(value = "设备号")
    private String equipmentNo;
}
