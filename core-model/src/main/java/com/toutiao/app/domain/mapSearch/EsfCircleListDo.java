package com.toutiao.app.domain.mapSearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by CuiShihao on 2018/11/29
 */
@Data
public class EsfCircleListDo {

    @ApiModelProperty(value = "房源信息", name = "sellHousesSearchDos")
    private EsfMapHouseDo esfMapHouseDo;

    @ApiModelProperty(value = "小区信息", name = "plotDetailsFewDo")
    private EsfMapCommunityDo esfMapCommunityDo;
}
