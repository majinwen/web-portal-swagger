package com.toutiao.app.domain.mapSearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/11/29
 */
@Data
public class EsfCircleListDomain {

    @ApiModelProperty("搜索到的所有小区的房源列表")
    private List<EsfCircleListDo> esfCircleListDos;
}
