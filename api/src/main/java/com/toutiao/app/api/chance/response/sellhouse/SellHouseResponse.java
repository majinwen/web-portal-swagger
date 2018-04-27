package com.toutiao.app.api.chance.response.sellhouse;

import com.toutiao.app.domain.sellhouse.SellHouseDo;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SellHouseResponse {

    private List<SellHouseDo> data;

    private Integer total;

}
