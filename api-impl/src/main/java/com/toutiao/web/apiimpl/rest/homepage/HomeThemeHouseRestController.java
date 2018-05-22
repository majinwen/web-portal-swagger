package com.toutiao.web.apiimpl.rest.homepage;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.api.chance.request.homepage.HomeThemeHouseRequest;
import com.toutiao.app.api.chance.response.homepage.HomeThemeHouseListResponse;
import com.toutiao.app.api.chance.response.sellhouse.SellHouseSearchDomainResponse;
import com.toutiao.app.domain.homepage.HomeThemeHouseDoQuery;
import com.toutiao.app.domain.homepage.HomeThemeHouseListDo;
import com.toutiao.app.domain.sellhouse.SellHouseSearchDomain;
import com.toutiao.app.service.homepage.HomePageRestService;
import com.toutiao.web.api.chance.request.advertisement.AggAdLandingRequest;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.domain.advertisement.AggAdLandingDo;
import com.toutiao.web.service.advertisement.AggAdLandingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/homePage/ad")
public class HomeThemeHouseRestController {

    @Autowired
    private HomePageRestService homePageRestService;
    @Autowired
    private AggAdLandingService aggAdLandingService;

    @RequestMapping(value = "/getHomeThemeHouse",method = RequestMethod.GET)
    private NashResult getHomeThemeHouse(HomeThemeHouseRequest homeThemeHouseRequest){
        HomeThemeHouseDoQuery homeThemeHouseDoQuery = new HomeThemeHouseDoQuery();
        BeanUtils.copyProperties(homeThemeHouseRequest,homeThemeHouseDoQuery);
        HomeThemeHouseListDo homeThemeHouse = homePageRestService.getHomeThemeHouse(homeThemeHouseDoQuery);
        HomeThemeHouseListResponse homeThemeHouseListResponse = JSON.parseObject(JSON.toJSONString(homeThemeHouse), HomeThemeHouseListResponse.class);
        return NashResult.build(homeThemeHouseListResponse);
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
