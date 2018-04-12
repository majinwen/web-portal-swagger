package com.toutiao.web.service.advertisement;

import com.toutiao.web.domain.advertisement.AggAdLandingDo;
import com.toutiao.web.domain.advertisement.RentHouseDomain;
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

    /**
     * 出租推荐房源
     * @param aggAdLandingDo
     * @return
     */
    RentHouseDomain getRentRecommendAdLanding(AggAdLandingDo aggAdLandingDo);

    /**
     * 出租录入房源
     * @param aggAdLandingDo
     * @return
     */
    RentHouseDomain getRentInputAdLanding(AggAdLandingDo aggAdLandingDo);

    /**
     * 出租导入房源
     * @param aggAdLandingDo
     * @return
     */
    RentHouseDomain getRentImportAdLanding(AggAdLandingDo aggAdLandingDo);

    /**
     * 出租推荐房源(v2)
     * @param aggAdLandingDo
     * @return
     */
    RentHouseDomain getRentRecommendAdLandingV2(AggAdLandingDo aggAdLandingDo);
    /**
     * 出租录入房源(v2)
     * @param aggAdLandingDo
     * @return
     */
    RentHouseDomain getRentInputAdLandingV2(AggAdLandingDo aggAdLandingDo);

    /**
     * 出租导入房源(v2)
     * @param aggAdLandingDo
     * @return
     */
    RentHouseDomain getRentImportAdLandingV2(AggAdLandingDo aggAdLandingDo);


}
