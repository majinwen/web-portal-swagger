package com.toutiao.app.domain.homepage;

import lombok.Data;

import java.util.List;

@Data
public class HomePageNearEsfListDo {
    /**
     * 小区
     */
    private List<HomePageNearEsfDo> data;
    /**
     * 房源总数
     */
    private Integer totalNum;
}
