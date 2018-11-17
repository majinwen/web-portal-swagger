//package com.toutiao.web.apiimpl.rest.sellhouse;
//
//
//import com.toutiao.app.api.chance.request.sellhouse.NearBySellHousesRequest;
//import com.toutiao.app.api.chance.response.sellhouse.NearBySellHouseDomainResponse;
//import com.toutiao.app.domain.sellhouse.NearBySellHouseDomain;
//import com.toutiao.app.domain.sellhouse.NearBySellHouseQueryDo;
//import com.toutiao.app.service.sellhouse.NearSellHouseRestService;
//import com.toutiao.web.common.restmodel.NashResult;
//import com.toutiao.web.common.util.city.CityUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequestMapping("/rest/esf/nearby")
//public class NearSellHouseRestController {
//
//    @Autowired
//    private NearSellHouseRestService nearSellHouseRestService;
//
//    /**
//     * 二手房附近5km列表
//     * @param nearBySellHousesRequest
//     * @return
//     */
//    @RequestMapping("/getNearBySellHouses")
//    @ResponseBody
//    public NashResult getSellHouseByHouseIdAndLocation(@Validated NearBySellHousesRequest nearBySellHousesRequest) {
//
//        NearBySellHouseDomainResponse nearBySellHouseDomainResponse=new NearBySellHouseDomainResponse();
//        NearBySellHouseQueryDo nearBySellHouseQueryDo=new NearBySellHouseQueryDo();
//        BeanUtils.copyProperties(nearBySellHousesRequest,nearBySellHouseQueryDo);
//        NearBySellHouseDomain nearBySellHouseDomain =  nearSellHouseRestService.getSellHouseByHouseIdAndLocation(nearBySellHouseQueryDo, CityUtils.getCity());
//        BeanUtils.copyProperties(nearBySellHouseDomain,nearBySellHouseDomainResponse);
//        return NashResult.build(nearBySellHouseDomainResponse);
//    }
//
//}
