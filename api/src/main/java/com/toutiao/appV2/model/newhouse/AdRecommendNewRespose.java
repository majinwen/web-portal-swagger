package com.toutiao.appV2.model.newhouse;

import com.toutiao.app.api.chance.response.homepage.HomePageNewHouseResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author : zym
 * @date : 2018/11/16 18:49
 * @desc :
 */
@Data
@Builder
public class AdRecommendNewRespose {
    private List<HomePageNewHouseResponse> data;

    private Integer totalNum;
}
