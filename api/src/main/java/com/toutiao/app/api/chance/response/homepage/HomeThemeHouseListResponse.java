package com.toutiao.app.api.chance.response.homepage;

import com.toutiao.app.domain.homepage.HomeThemeHouseDo;
import lombok.Data;

import java.util.List;

@Data
public class HomeThemeHouseListResponse {
    /**
     * 二手房推荐房源
     */
    private List<HomeThemeHouseDo> data;
    /**
     * 房源总数
     */
    private Integer totalNum;
}
