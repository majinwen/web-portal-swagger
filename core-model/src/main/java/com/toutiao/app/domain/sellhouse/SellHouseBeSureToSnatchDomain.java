package com.toutiao.app.domain.sellhouse;

import lombok.Data;

import java.util.List;
@Data
public class SellHouseBeSureToSnatchDomain {
    private List<SellHouseBeSureToSnatchDo> data;
    private Integer subscribeId;
    private long totalCount;

 }
