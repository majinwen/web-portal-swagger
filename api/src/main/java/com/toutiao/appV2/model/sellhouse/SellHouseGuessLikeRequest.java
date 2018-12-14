package com.toutiao.appV2.model.sellhouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by wk on 2018/12/13.
 */
@Data
public class SellHouseGuessLikeRequest {

    @ApiModelProperty(value = "二手房总价", name = "totalPrice")
    private double totalPrice = 0;

    @ApiModelProperty(value = "商圈id", name = "areaId")
    private Integer[] areaId = null;

    @ApiModelProperty(value = "居室", name = "layoutId")
    private  Integer [] layoutId = null;

    @ApiModelProperty(value = "厅", name = "hall")
    private Integer hall = 0;

    @ApiModelProperty(value = "页数",name = "pageNum")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页条数",name = "pageSize")
    private Integer pageSize = 10;

}
