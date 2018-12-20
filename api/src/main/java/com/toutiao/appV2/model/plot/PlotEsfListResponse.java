package com.toutiao.appV2.model.plot;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * PlotEsfListResponse
 */
@Validated
@Data
public class PlotEsfListResponse {

    @ApiModelProperty(value = "二手房列表", name = "data")
    private List<PlotEsfResponse> data;

    @ApiModelProperty(value = "总条数", name = "totalNum")
    private Integer totalNum;

}

