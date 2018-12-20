package com.toutiao.appV2.model.mapSearch;

import com.toutiao.app.domain.mapSearch.EsfCircleListDo;
import com.toutiao.app.domain.mapSearch.EsfHouseListDomain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/11/29
 */
@Data
public class EsfCircleListResponse {

    @ApiModelProperty("搜索到的所有小区的房源列表")
    private List<EsfCircleListDo> esfCircleListDos;
}
