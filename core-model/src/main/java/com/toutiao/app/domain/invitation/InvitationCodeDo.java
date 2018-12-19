package com.toutiao.app.domain.invitation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class InvitationCodeDo {
    private Integer id;

    private Date createTime;

    @ApiModelProperty(value = "邀请码")
    private Integer code;

    @ApiModelProperty(value = "邀请次数")
    private Integer inviteTotal;

    private String userId;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邀请记录")
    private List<InviteHistoryDo> invateHistoryDos;
}
