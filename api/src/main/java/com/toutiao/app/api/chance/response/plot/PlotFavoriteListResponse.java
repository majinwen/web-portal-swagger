package com.toutiao.app.api.chance.response.plot;

import com.toutiao.app.domain.plot.UserFavoritePlotDo;
import lombok.Data;

import java.util.List;

@Data
public class PlotFavoriteListResponse {

    /**
     * 小区附近列表
     */
    private List<UserFavoritePlotDo> data;

    /**
     * 查询结果总量
     */
    private Long totalNum;

}
