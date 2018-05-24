package com.toutiao.app.api.chance.response.sellhouse;

import lombok.Data;

import java.util.List;

@Data
public class ChooseSellHouseResponse {

    private List<SellHouseResponse> sellHouseList;

    private long total;
}
