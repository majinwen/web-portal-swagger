package com.toutiao.web.apiimpl.rest.rent;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.api.chance.request.rent.NearHouseListRequest;
import com.toutiao.app.api.chance.response.rent.RentDetailFewResponseList;
import com.toutiao.app.domain.rent.NearHouseListDoQuery;
import com.toutiao.app.domain.rent.RentDetailsListDo;
import com.toutiao.app.service.rent.NearRentHouseRestService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 租房附近5km
 */
@RestController
@RequestMapping("/rest/rent/nearby")
public class NearRentHouseRestController {

    @Autowired
    private NearRentHouseRestService nearRentHouseRestService;

    @RequestMapping(value = "/getNearRentHouseByLocation",method = RequestMethod.GET)
    private NashResult getNearHouseByLocation(@Validated NearHouseListRequest nearHouseListRequest){
        NearHouseListDoQuery nearHouseListDoQuery = new NearHouseListDoQuery();
        BeanUtils.copyProperties(nearHouseListRequest,nearHouseListDoQuery);
        RentDetailsListDo rentDetailsListDo = nearRentHouseRestService.queryNearHouseByLocation(nearHouseListDoQuery);
        RentDetailFewResponseList rentDetailFewResponseList = JSON.parseObject(JSON.toJSONString(rentDetailsListDo), RentDetailFewResponseList.class);
        return NashResult.build(rentDetailFewResponseList);
    }
}
