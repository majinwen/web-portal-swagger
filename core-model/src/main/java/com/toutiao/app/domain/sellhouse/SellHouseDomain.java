package com.toutiao.app.domain.sellhouse;

import lombok.Data;

import java.util.List;

@Data
public class SellHouseDomain {

    private List<SellHouseDo> sellHouseList;

    private Integer total;
}
