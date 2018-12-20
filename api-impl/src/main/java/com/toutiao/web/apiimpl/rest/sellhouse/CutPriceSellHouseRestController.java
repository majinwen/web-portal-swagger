//package com.toutiao.web.apiimpl.rest.sellhouse;
//
//import com.toutiao.app.api.chance.request.sellhouse.MustBuyShellHouseRequest;
//import com.toutiao.app.api.chance.response.sellhouse.MustBuyShellHouseResponse;
//import com.toutiao.app.domain.sellhouse.MustBuyShellHouseDoQuery;
//import com.toutiao.app.domain.sellhouse.MustBuyShellHouseDomain;
//import com.toutiao.app.service.sellhouse.MustBuySellHouseRestService;
//import com.toutiao.web.common.restmodel.NashResult;
//import com.toutiao.web.common.util.city.CityUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 降价房专题页控制器
// *
// * @author zym
// */
//@RestController
//@RequestMapping("/rest/esf/cutPrice")
//public class CutPriceSellHouseRestController {
//    @Autowired
//    private MustBuySellHouseRestService mustBuySellHouseRestService;
//
//    /**
//     * 专题页获取降价房List
//     */
//    @RequestMapping(value = "/getCutPriceShellHouse", method = RequestMethod.GET)
//    @ResponseBody
//    public NashResult getCutPriceShellHouse(MustBuyShellHouseRequest mustBuyShellHouseRequest) {
//        MustBuyShellHouseDoQuery mustBuyShellHouseDoQuery = new MustBuyShellHouseDoQuery();
//        BeanUtils.copyProperties(mustBuyShellHouseRequest, mustBuyShellHouseDoQuery);
//        MustBuyShellHouseDomain cutPriceShellHouses = mustBuySellHouseRestService.getMustBuySellHouse(mustBuyShellHouseDoQuery, 1, CityUtils.getCity());
//        MustBuyShellHouseResponse mustBuyShellHouseResponse = new MustBuyShellHouseResponse();
//        BeanUtils.copyProperties(cutPriceShellHouses, mustBuyShellHouseResponse);
//        return NashResult.build(cutPriceShellHouses);
//    }
//}
