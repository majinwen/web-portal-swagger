package com.toutiao.web.dao.entity.invitation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class InviteHistory {
    /**
     * Id
     */
    private Integer id;

    @ApiModelProperty(value = "邀请码")
    private Integer invitationCode;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createTime;

    @ApiModelProperty(value = "是否有效", hidden = true)
    private Short isValid;

    @ApiModelProperty(value = "设备号", hidden = true)
    private Date payTime;

    @ApiModelProperty(value = "设备号")
    private String equipmentNo;

    @ApiModelProperty(value = "用户手机号")
    private String phone;
}