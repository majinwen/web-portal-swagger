package com.toutiao.appV2.model.request.invitation;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;

@Data
public class SuperInviteHistoryRequest{

    @ApiParam(value = "直接邀请码/一级邀请码")
    private Integer firstLevelCode;

    @ApiParam(value = "二级邀请码")
    private Integer secondLevelCode;

    @ApiParam(value = "三级邀请码")
    private Integer thirdLevelCode;

    @ApiParam(value = "设备号")
    private String equipmentNo;

    @ApiParam(value = "手机号")
    private String phone;

    @ApiParam(value = "创建时间")
    private Date createTime;

    @ApiParam(value = "页码", defaultValue = "1")
    private Integer pageNum = 1;

    @ApiParam(value = "页容量", defaultValue = "10")
    private Integer pageSize = 10;
}
