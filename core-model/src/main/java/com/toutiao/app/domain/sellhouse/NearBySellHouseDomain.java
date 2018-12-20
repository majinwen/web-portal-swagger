package com.toutiao.app.domain.sellhouse;

import lombok.Data;

import java.util.List;
@Data
public class NearBySellHouseDomain {


    private List<NearBySellHousesDo> data;

    private long totalNum;


}
