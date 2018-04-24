package com.toutiao.app.api.chance.response.sellhouse;

import com.toutiao.app.domain.sellhouse.NearBySellHouseDomain;
import lombok.Data;

import java.util.List;

@Data
public class NearBySellHouseDomainResponse {

    List<NearBySellHousesResponse> nearBySellHousesResponses;

    private long TotalCount;
}
