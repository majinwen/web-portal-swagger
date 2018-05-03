package com.toutiao.app.domain.favorite.newhouse;

import lombok.Data;

import java.util.List;

@Data
public class NewHouseFavoriteDomain {

    private List<NewHouseFavoriteDo> data;

    private Long totalNum;

}
