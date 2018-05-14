package com.toutiao.web.apiimpl.impl.advertisement;


import com.toutiao.app.api.chance.response.sellhouse.SellHouseSearchDomainResponse;
import com.toutiao.app.domain.sellhouse.SellHouseSearchDomain;
import com.toutiao.web.api.chance.request.advertisement.AggAdLandingRequest;
import com.toutiao.web.api.chance.response.advertisement.RentHouseResponse;
import com.toutiao.web.api.chance.response.advertisement.SellHouseResponse;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.domain.advertisement.AggAdLandingDo;
import com.toutiao.web.domain.advertisement.RentHouseDomain;
import com.toutiao.web.domain.advertisement.SellHouseDomain;
import com.toutiao.web.service.advertisement.AggAdLandingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * 广告位
 * Created by 18710 on 2018/3/9.
 */

@Controller
@RequestMapping("/agg/ad")
public class AggAdLandingController {

    @Autowired
    private AggAdLandingService aggAdLandingService;


    @RequestMapping(value = "/queryAggAdLanding")
    @ResponseBody
    public NashResult queryAggAdLanding(AggAdLandingRequest aggAdLandingRequest){

        AggAdLandingDo aggAdLandingDo = new AggAdLandingDo();
        BeanUtils.copyProperties(aggAdLandingRequest, aggAdLandingDo);
        Map<String, Object> esfMap = aggAdLandingService.getAdLanding(aggAdLandingDo);

        return NashResult.build(esfMap.get("data"));
    }

    /**
     * 推荐（出售[二手房]）
     * @param aggAdLandingRequest
     * @return
     */
    @RequestMapping(value = "/queryRecommendAggAdLanding")
    @ResponseBody
    public NashResult queryRecommendAggAdLanding(AggAdLandingRequest aggAdLandingRequest){

        AggAdLandingDo aggAdLandingDo = new AggAdLandingDo();
        BeanUtils.copyProperties(aggAdLandingRequest, aggAdLandingDo);
        SellHouseDomain sellHouseDomain = aggAdLandingService.getRecommendAdLanding(aggAdLandingDo);
        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        BeanUtils.copyProperties(sellHouseDomain,sellHouseResponse);

        return NashResult.build(sellHouseResponse);
    }


    /**
     * 认领（出售[二手房]）
     * @param aggAdLandingRequest
     * @return
     */
    @RequestMapping(value = "/queryClaimAggAdLanding")
    @ResponseBody
    public NashResult queryClaimAggAdLanding(AggAdLandingRequest aggAdLandingRequest){

        AggAdLandingDo aggAdLandingDo = new AggAdLandingDo();
        BeanUtils.copyProperties(aggAdLandingRequest, aggAdLandingDo);
        SellHouseDomain sellHouseDomain = aggAdLandingService.getClaimAdLanding(aggAdLandingDo);
        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        BeanUtils.copyProperties(sellHouseDomain,sellHouseResponse);

        return NashResult.build(sellHouseResponse);
    }

    /**
     * 经纪公司导入未认领（出售[二手房]）
     * @param aggAdLandingRequest
     * @return
     */
    @RequestMapping(value = "/queryUnClaimAggAdLanding")
    @ResponseBody
    public NashResult queryUnClaimAggAdLanding(AggAdLandingRequest aggAdLandingRequest){

        AggAdLandingDo aggAdLandingDo = new AggAdLandingDo();
        BeanUtils.copyProperties(aggAdLandingRequest, aggAdLandingDo);
        SellHouseDomain sellHouseDomain = aggAdLandingService.getApiImportAdLanding(aggAdLandingDo);
        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        BeanUtils.copyProperties(sellHouseDomain,sellHouseResponse);

        return NashResult.build(sellHouseResponse);
    }



    @RequestMapping("/cpc1")
    @ResponseBody
    public NashResult advertisementCpc1() {
        Map<String,Object> advertisementResult = aggAdLandingService.advertisementCpc_1();

        return NashResult.build(advertisementResult.get("data"));
    }


    /**
     * cpc出租房源推荐
     * @param aggAdLandingRequest
     * @return
     */
    @RequestMapping(value = "/queryRentRecommendAggAdLanding")
    @ResponseBody
    public NashResult queryRentRecommendAggAdLanding(AggAdLandingRequest aggAdLandingRequest){

        AggAdLandingDo aggAdLandingDo = new AggAdLandingDo();
        BeanUtils.copyProperties(aggAdLandingRequest, aggAdLandingDo);
        RentHouseDomain rentHouseDomain = aggAdLandingService.getRentRecommendAdLandingV2(aggAdLandingDo);
        RentHouseResponse rentHouseResponse = new RentHouseResponse();
        BeanUtils.copyProperties(rentHouseDomain,rentHouseResponse);

        return NashResult.build(rentHouseResponse);
    }

    /**
     * cpc 录入房源推荐
     * @param aggAdLandingRequest
     * @return
     */
    @RequestMapping(value = "/queryRentInputAggAdLanding")
    @ResponseBody
    public NashResult queryRentInputAggAdLanding(AggAdLandingRequest aggAdLandingRequest){

        AggAdLandingDo aggAdLandingDo = new AggAdLandingDo();
        BeanUtils.copyProperties(aggAdLandingRequest, aggAdLandingDo);
        RentHouseDomain rentHouseDomain = aggAdLandingService.getRentInputAdLandingV2(aggAdLandingDo);
        RentHouseResponse rentHouseResponse = new RentHouseResponse();
        BeanUtils.copyProperties(rentHouseDomain,rentHouseResponse);

        return NashResult.build(rentHouseResponse);
    }

    /**
     * cpc 导入房源推荐
     * @param aggAdLandingRequest
     * @return
     */
    @RequestMapping(value = "/queryRentImportAggAdLanding")
    @ResponseBody
    public NashResult queryRentImportAggAdLanding(AggAdLandingRequest aggAdLandingRequest){

        AggAdLandingDo aggAdLandingDo = new AggAdLandingDo();
        BeanUtils.copyProperties(aggAdLandingRequest, aggAdLandingDo);
        RentHouseDomain rentHouseDomain = aggAdLandingService.getRentImportAdLandingV2(aggAdLandingDo);
        RentHouseResponse rentHouseResponse = new RentHouseResponse();
        BeanUtils.copyProperties(rentHouseDomain,rentHouseResponse);

        return NashResult.build(rentHouseResponse);
    }


    @RequestMapping(value = "/querySellHouseTop10")
    @ResponseBody
    public NashResult querySellHouseTop10(){

        SellHouseDomain sellHouseDomain = aggAdLandingService.getSellHouseTop10();
        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        BeanUtils.copyProperties(sellHouseDomain,sellHouseResponse);
        return NashResult.build(sellHouseResponse);
    }

    @RequestMapping(value = "/querySellHouseByPrice")
    @ResponseBody
    public NashResult querySellHouseByPrice(AggAdLandingRequest aggAdLandingRequest){
        SellHouseSearchDomainResponse sellHouseSearchDomainResponse =  new SellHouseSearchDomainResponse();
        AggAdLandingDo aggAdLandingDo = new AggAdLandingDo();
        BeanUtils.copyProperties(aggAdLandingRequest, aggAdLandingDo);

        SellHouseSearchDomain sellHouseSearchDomain = aggAdLandingService.getSellHouseByPrice(aggAdLandingDo);
        BeanUtils.copyProperties(sellHouseSearchDomain,sellHouseSearchDomainResponse);

        return NashResult.build(sellHouseSearchDomainResponse);
    }

}
