package com.toutiao.app.domain.favorite.sellhouse;

import lombok.Data;

import java.util.List;

@Data
public class SellHouseFavoriteDomain {

    private List<SellHouseFavoriteDo> data;

    private Long totalNum;

}
