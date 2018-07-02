package com.toutiao.web.apiimpl.rest.sellhouse;


import com.toutiao.app.api.chance.request.sellhouse.SellHouseRequest;
import com.toutiao.app.api.chance.response.sellhouse.SellHouseResponse;
import com.toutiao.app.domain.sellhouse.SellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseDomain;
import com.toutiao.app.service.sellhouse.SellHouseDetailTopicsService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 专题房源详情页
 *
 */
@RestController
@RequestMapping("/rest/esf/topic")
public class SellHouseDetailTopicsRestController {

    @Autowired
    private SellHouseDetailTopicsService sellHouseDetailTopicsService;



    @RequestMapping(value ="/getNearbyTopicsSellHouseDetail", method = RequestMethod.GET)
    @ResponseBody
    public NashResult getNearbyTopicsSellHouseDetail(@Validated(First.class)  SellHouseRequest sellHouseRequest) {

        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
        SellHouseDomain sellHouseDomain = sellHouseDetailTopicsService.getNearbyTopicsSellHouse(sellHouseDoQuery);
        BeanUtils.copyProperties(sellHouseDomain,sellHouseResponse);
        return NashResult.build(sellHouseResponse);
    }




}
