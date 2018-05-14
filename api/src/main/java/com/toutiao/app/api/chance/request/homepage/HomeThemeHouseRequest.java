package com.toutiao.app.api.chance.request.homepage;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import lombok.Data;

@Data
public class HomeThemeHouseRequest extends BaseQueryRequest {

    /**
     * 是否有地铁(有:1,没有:2)
     */
    private Integer hasSubway;

}
