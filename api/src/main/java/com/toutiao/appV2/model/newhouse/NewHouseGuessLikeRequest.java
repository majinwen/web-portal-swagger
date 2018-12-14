package com.toutiao.appV2.model.newhouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * Created by wk on 2018/12/13.
 */
@Data
public class NewHouseGuessLikeRequest {

    @ApiModelProperty(value = "区域",name = "districtId")
    private Integer districtId = 0;

    @ApiModelProperty(value = "均价",name = "avgPrice")
    private double avgPrice = 0;

    @ApiModelProperty(value = "总价",name = "totalPrice")
    private double totalPrice = 0;

    @ApiModelProperty(value = "页数",name = "pageNum")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页条数",name = "pageSize")
    private Integer pageSize = 10;
}
