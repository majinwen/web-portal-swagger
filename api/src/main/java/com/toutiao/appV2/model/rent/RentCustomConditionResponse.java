package com.toutiao.appV2.model.rent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.rent.RentCustomConditionDo;
import com.toutiao.app.domain.rent.RentCustomDo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/12/12
 */
@Data
public class RentCustomConditionResponse {


    @JsonProperty("rentCustomDo")
    @ApiModelProperty("id与名称")
    private RentCustomDo rentCustomDo = null;

    @JsonProperty("rentCustomConditionDos")
    @ApiModelProperty("房源统计")
    private List<RentCustomConditionDo> rentCustomConditionDos = null;
}
