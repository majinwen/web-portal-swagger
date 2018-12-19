package com.toutiao.app.api.chance.response.homepage;

import lombok.Data;

/**
 * Created by CuiShihao on 2018/10/19
 */
@Data
public class HomePageNewCountResponse {

    //全部楼盘
    private long buildingCount;

    //优惠楼盘
    private long preferentialCount;

    //在售楼盘
    private long onsaleCount;

    //即将开盘
    private long forthcomingCount;
}
