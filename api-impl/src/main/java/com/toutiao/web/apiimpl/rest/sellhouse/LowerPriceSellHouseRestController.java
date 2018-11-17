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
// * 捡漏房专题页控制器
// *
// * @author zym
// */
//@RestController
//@RequestMapping("/rest/esf/lowerPrice")
//public class LowerPriceSellHouseRestController {
//    @Autowired
//    private MustBuySellHouseRestService mustBuySellHouseRestService;
//
//    /**
//     * 获取捡漏房数据
//     */
//    @RequestMapping(value = "/getLowerPriceShellHouse", method = RequestMethod.GET)
//    @ResponseBody
//    public NashResult getLowerPriceShellHouse(MustBuyShellHouseRequest mustBuyShellHouseRequest) {
//        MustBuyShellHouseDoQuery mustBuyShellHouseDoQuery = new MustBuyShellHouseDoQuery();
//        BeanUtils.copyProperties(mustBuyShellHouseRequest, mustBuyShellHouseDoQuery);
//        MustBuyShellHouseDomain lowerPriceShellHouses = mustBuySellHouseRestService.getMustBuySellHouse(mustBuyShellHouseDoQuery, 2, CityUtils.getCity());
//        MustBuyShellHouseResponse lowerPriceShellHouseResponse = new MustBuyShellHouseResponse();
//        BeanUtils.copyProperties(lowerPriceShellHouses, lowerPriceShellHouseResponse);
//        return NashResult.build(lowerPriceShellHouses);
//    }
//}
