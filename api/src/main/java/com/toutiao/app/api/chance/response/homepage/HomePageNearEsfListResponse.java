package com.toutiao.app.api.chance.response.homepage;

import com.toutiao.app.domain.homepage.HomePageNearEsfDo;
import lombok.Data;

import java.util.List;

@Data
public class HomePageNearEsfListResponse {
    /**
     * 二手房
     */
    private List<HomePageNearEsfDo> data;
    /**
     * 房源总数
     */
    private Integer totalNum;
}
