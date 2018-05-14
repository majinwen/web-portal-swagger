package com.toutiao.app.domain.homepage;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

@Data
public class HomeThemeHouseDoQuery extends QueryDo{
    /**
     * 是否有地铁(有:1,没有:2)
     */
    private Integer hasSubway;
}
