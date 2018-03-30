package com.toutiao.web.service.advertisement;

import com.toutiao.web.domain.advertisement.AggAdLandingDo;
import com.toutiao.web.domain.advertisement.SellHouseAggAdLandingDo;
import com.toutiao.web.domain.advertisement.SellHouseDomain;

import java.util.List;
import java.util.Map;

public interface AggAdLandingService {


    Map<String,Object> getAdLanding(AggAdLandingDo aggAdLandingDo);


    Map<String,Object> advertisementCpc_1();


    SellHouseDomain getRecommendAdLanding(AggAdLandingDo aggAdLandingDo);


    SellHouseDomain getClaimAdLanding(AggAdLandingDo aggAdLandingDo);


    SellHouseDomain getApiImportAdLanding(AggAdLandingDo aggAdLandingDo);

}
