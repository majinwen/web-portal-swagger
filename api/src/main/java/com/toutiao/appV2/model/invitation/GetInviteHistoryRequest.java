package com.toutiao.appV2.model.invitation;

import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * @author zym
 */
@Data
public class GetInviteHistoryRequest {

    @ApiParam(value = "邀请码", name = "code")
    private Integer code;

    @ApiParam(value = "页码", defaultValue = "1")
    private Integer pageNum = 1;

    @ApiParam(value = "页容量", defaultValue = "10")
    private Integer pageSize = 10;

}
