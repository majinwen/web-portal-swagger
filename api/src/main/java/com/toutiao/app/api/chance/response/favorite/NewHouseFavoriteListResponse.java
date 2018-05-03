package com.toutiao.app.api.chance.response.favorite;

import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteDo;
import com.toutiao.app.domain.plot.UserFavoritePlotDo;
import lombok.Data;

import java.util.List;

@Data
public class NewHouseFavoriteListResponse {

    private List<NewHouseFavoriteDo> data;

    private Long totalNum;

}
