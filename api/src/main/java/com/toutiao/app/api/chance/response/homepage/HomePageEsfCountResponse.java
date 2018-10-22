package com.toutiao.app.api.chance.response.homepage;

import lombok.Data;

/**
 * Created by CuiShihao on 2018/10/19
 */
@Data
public class HomePageEsfCountResponse {

    //待售房的数量
    private long forSaleCount;

    //降价房的数量
    private long reduceCount;

    //抢手房的数量
    private long hotSaleCount;

    //捡漏房的数量
    private long missingCount;
}
