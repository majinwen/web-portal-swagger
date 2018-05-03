package com.toutiao.app.api.chance.response.favorite;

import com.toutiao.app.domain.favorite.rent.RentFavoriteDo;
import lombok.Data;

import java.util.List;

@Data
public class RentFavoriteListResponse {


    private List<RentFavoriteDo> data;

    private Long totalNum;

}
