package com.toutiao.app.domain.mapSearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/11/26
 */
@Data
public class EsfHouseListDomain {

    @ApiModelProperty(value = "房源列表", name = "sellHousesSearchDos")
    private List<EsfMapHouseDo> esfMapHouseDos;

    @ApiModelProperty(value = "小区信息", name = "plotDetailsFewDo")
    private EsfMapCommunityDo esfMapCommunityDo;
}
