package com.toutiao.app.domain.sellhouse;

import lombok.Data;

import java.util.List;

@Data
public class SellHouseDomain {

    private List<SellHouseDo> data;

    private Integer totalNum;
}
