package com.toutiao.app.api.chance.response.mapSearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RentOfPlotListResPonse {

    @ApiModelProperty(value = "租房列表")
    private List<RentOfPlotResPonse> data;

    /**
     * 社区名称
     */
    @ApiModelProperty("社区名称")
    private String ploatName;

    /**
     * 社区ID
     */
    @ApiModelProperty("社区ID")
    private Integer newcode;


    @ApiModelProperty(value = "附近的地铁线")
    private String nearSubwayLine;

    @ApiModelProperty(value = "总套数")
    private Integer totalNum;
}
