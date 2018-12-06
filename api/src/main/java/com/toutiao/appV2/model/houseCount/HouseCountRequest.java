package com.toutiao.appV2.model.houseCount;

import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * Created by CuiShihao on 2018/12/6
 */
@Data
public class HouseCountRequest {

    @ApiParam(value = "关键字", name = "keyword")
    private String keyword;
}
