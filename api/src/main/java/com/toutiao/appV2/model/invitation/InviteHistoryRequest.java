package com.toutiao.appV2.model.invitation;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author zym
 */
@Data
public class InviteHistoryRequest {
    @ApiParam(value = "邀请码", required = true)
    @NotNull(message = "邀请码不能为空")
    private Integer invitationCode;
}
