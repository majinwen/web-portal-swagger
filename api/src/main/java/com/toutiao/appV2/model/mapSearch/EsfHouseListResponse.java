package com.toutiao.appV2.model.mapSearch;

import com.toutiao.app.domain.mapSearch.EsfMapCommunityDo;
import com.toutiao.app.domain.mapSearch.EsfMapHouseDo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/11/26
 */
@Data
@ApiModel(value = "EsfHouseListResponse", description = "EsfHouseListResponse")
public class EsfHouseListResponse {

    @ApiModelProperty(value = "房源列表", name = "sellHousesSearchDos")
    private List<EsfMapHouseDo> esfMapHouseDos;

    @ApiModelProperty(value = "小区信息", name = "plotDetailsFewDo")
    private EsfMapCommunityDo esfMapCommunityDo;
}
