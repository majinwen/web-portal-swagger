package com.toutiao.appV2.model.response.invitation;

import com.toutiao.app.domain.invitation.InviteHistoryDo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "邀请码信息Response")
public class InvitationResponse {
    private Integer id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "邀请码")
    private Integer code;

    @ApiModelProperty(value = "邀请次数")
    private Integer inviteTotal;

    private String userId;

    @ApiModelProperty(value = "邀请记录")
    private List<InviteHistoryDo> invateHistoryDos;
}
