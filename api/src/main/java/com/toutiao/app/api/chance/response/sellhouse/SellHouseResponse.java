package com.toutiao.app.api.chance.response.sellhouse;

import com.toutiao.app.domain.sellhouse.SellHouseDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SellHouseResponse {

    @ChangeName("data")
    private List<SellHouseDo> sellHouseList;
    @ChangeName("totalNum")
    private Integer total;

}
