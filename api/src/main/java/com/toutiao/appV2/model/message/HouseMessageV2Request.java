package com.toutiao.appV2.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author : zym
 * @date : 2018/12/6 10:25
 * @desc :
 */
@Data
public class HouseMessageV2Request {
    @NotNull(message = "类型")
    @ApiParam(value = "类型(1-下架变价，2-同类上新)", required = true)
    private Integer type = null;

    @ApiParam(value = "最后消息Id")
    private Integer lastMessageId = null;
}
