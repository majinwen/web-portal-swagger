package com.toutiao.app.domain.homepage;

import lombok.Data;

import java.util.List;

@Data
public class HomeThemeHouseListDo {
    /**
     * 二手房推荐房源
     */
    private List<HomeThemeHouseDo> data;
    /**
     * 房源总数
     */
    private Integer totalNum;
}
