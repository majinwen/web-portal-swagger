package com.toutiao.app.api.chance.response.sellhouse;

import com.toutiao.app.domain.sellhouse.NearBySellHousesDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.List;

@Data
public class NearBySellHouseDomainResponse {

    @ChangeName("data")
    List<NearBySellHousesDo> nearBySellHousesDos;
    @ChangeName("totalNum")
    private long totalCount;
}
