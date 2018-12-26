package com.toutiao.appV2.model.sellhouse;

import com.toutiao.app.domain.sellhouse.LowPriceSellHouseThemeDo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CutPriceSellHouseThemeResponse
 * @Author jiangweilong
 * @Date 2018/12/24 4:31 PM
 * @Description:
 **/

@Data
@ApiModel(value = "LowPriceSellHouseThemeResponse", description = "LowPriceSellHouseThemeResponse")
public class LowPriceSellHouseThemeResponse {


    @ApiModelProperty(value = "捡漏房数据", name = "data")
    private List<LowPriceSellHouseThemeDo> data;

    @ApiModelProperty(value = "是否被订阅", name = "subscribeId")
    private Integer subscribeId;

    @ApiModelProperty(value = "总条数", name = "totalCount")
    private long totalCount;

}
