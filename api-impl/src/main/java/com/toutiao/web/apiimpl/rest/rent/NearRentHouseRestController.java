package com.toutiao.web.apiimpl.rest.rent;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.api.chance.request.rent.NearHouseRequest;
import com.toutiao.app.api.chance.response.rent.RentDetailFewResponseList;
import com.toutiao.app.domain.rent.NearHouseDo;
import com.toutiao.app.domain.rent.RentDetailsDoList;
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
    private NashResult getNearHouseByLocation(@Validated NearHouseRequest nearHouseRequest){
        NearHouseDo nearHouseDo = new NearHouseDo();
        BeanUtils.copyProperties(nearHouseRequest,nearHouseDo);
        RentDetailsDoList rentDetailsDoList = nearRentHouseRestService.queryNearHouseByLocation(nearHouseDo);
        RentDetailFewResponseList rentDetailFewResponseList = JSON.parseObject(JSON.toJSONString(rentDetailsDoList), RentDetailFewResponseList.class);
        return NashResult.build(rentDetailFewResponseList);
    }
}
