package com.toutiao.app.domain.sellhouse;

import lombok.Data;

import java.util.List;

@Data
public class ChooseSellHouseDomain {

    private List<SellHouseDo> sellHouseList;

    private long total;
}
