package com.toutiao.app.api.chance.response.sellhouse;

import com.toutiao.app.domain.sellhouse.SellHousesSearchDo;
import lombok.Data;

import java.util.List;

@Data
public class SellHouseSearchDomainResponse {


    private List<SellHousesSearchDo> data;

    private Integer totalNum;
}
