package com.toutiao.app.domain.invitation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SuperInviteHistoryDo {
    /**
     * Id
     */
    private Integer id;

    @ApiModelProperty(value = "一级邀请码")
    private String firstLevelCode;

    @ApiModelProperty(value = "二级邀请码")
    private String secondLevelCode;

    @ApiModelProperty(value = "三级邀请码")
    private String thirdLevelCode;

    private String userId;

    private Date createTime;

    @ApiModelProperty(value = "设备号")
    private String equipmentNo;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "被邀人邀请码")
    private String yourCode;

    private Short isValid;

    private Date payTime;
}
