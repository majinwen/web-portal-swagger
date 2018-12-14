package com.toutiao.appV2.model.sellhouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by wk on 2018/12/13.
 */
@Data
public class SellHouseGuessLikeResponse {

    @ApiModelProperty(value = "搜索二手房列表", name = "data")
    private List<SellHousesSearchDo> data;

    @ApiModelProperty(value = "总条数", name = "totalNum")
    private long totalNum;

}
