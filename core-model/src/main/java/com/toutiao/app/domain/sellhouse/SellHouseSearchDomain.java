package com.toutiao.app.domain.sellhouse;

import lombok.Data;

import java.util.List;

@Data
public class SellHouseSearchDomain {

    private List<SellHousesSearchDo> data;

    private Integer totalNum;
}
