package com.toutiao.app.domain.homepage;

import lombok.Data;

import java.util.List;

@Data
public class HomePageNearEsfListDo {
    /**
     * 二手房
     */
    private List<HomePageNearEsfDo> data;
    /**
     * 房源总数
     */
    private Integer totalNum;
}
