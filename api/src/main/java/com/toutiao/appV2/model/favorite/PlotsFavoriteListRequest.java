package com.toutiao.appV2.model.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * PlotsFavoriteListRequest
 */
@Data
@ApiModel(value = "PlotsFavoriteListRequest", description = "PlotsFavoriteListRequest")
public class PlotsFavoriteListRequest {

    @ApiModelProperty(value = "用户id", name = "userId", required = true)
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @ApiModelProperty(value = "当前页", name = "userId")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页大小", name = "userId")
    private Integer size = 10;

}

