package com.toutiao.app.api.chance.response.favorite;

import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteDo;
import lombok.Data;

import java.util.List;

@Data
public class SellHouseFavoriteListResponse {


    private List<SellHouseFavoriteDo> data;

    private Long totalNum;

}
