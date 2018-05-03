package com.toutiao.app.domain.favorite.rent;


import lombok.Data;

import java.util.List;

@Data
public class RentFavoriteDomain {

    private List<RentFavoriteDo> data;

    private Long totalNum;

}
