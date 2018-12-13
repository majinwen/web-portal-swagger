package com.toutiao.appV2.model.subway;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by CuiShihao on 2018/12/13
 */
@Data
public class SubwayLineResponse {

    @JsonProperty("lineId")
    @ApiModelProperty("地铁线id")
    private Integer lineId;

    @JsonProperty("subwayName")
    @ApiModelProperty("地铁线名称")
    private String subwayName;

    @JsonProperty("cityId")
    @ApiModelProperty("城市id")
    private Integer cityId;

}
