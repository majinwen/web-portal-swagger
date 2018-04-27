package com.toutiao.web.apiimpl.rest.sellhouse;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.sellhouse.NearBySellHousesRequest;
import com.toutiao.app.api.chance.response.sellhouse.NearBySellHouseDomainResponse;
import com.toutiao.app.api.chance.response.sellhouse.NearBySellHousesResponse;
import com.toutiao.app.domain.sellhouse.NearBySellHouseDomain;
import com.toutiao.app.domain.sellhouse.NearBySellHousesDo;
import com.toutiao.app.service.sellhouse.NearSellHouseRestService;
import com.toutiao.web.common.restmodel.InvokeResult;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/esf/nearby")
public class NearSellHouseRestController {

    @Autowired
    private NearSellHouseRestService nearSellHouseRestService;

    /**
     * 二手房附近5km列表
     * @param nearBySellHousesRequest
     * @return
     */
    @RequestMapping("/getNearBySellHouses")
    @ResponseBody
    public InvokeResult getSellHouseByHouseIdAndLocation(@Validated NearBySellHousesRequest nearBySellHousesRequest) {

        NearBySellHouseDomainResponse nearBySellHouseDomainResponse=new NearBySellHouseDomainResponse();
        NearBySellHousesDo nearBySellHousesDo=new NearBySellHousesDo();
        BeanUtils.copyProperties(nearBySellHousesRequest,nearBySellHousesDo);
        NearBySellHouseDomain nearBySellHouseDomain =  nearSellHouseRestService.getSellHouseByHouseIdAndLocation(nearBySellHousesDo);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(nearBySellHouseDomain.getNearBySellHousesDos()));
        List<NearBySellHousesResponse> nearBySellHousesResponses= JSONObject.parseArray(json.toJSONString(),NearBySellHousesResponse.class);
        nearBySellHouseDomainResponse.setNearBySellHousesResponses(nearBySellHousesResponses);
        nearBySellHouseDomainResponse.setTotalCount(nearBySellHouseDomain.getTotalCount());
        return InvokeResult.build(nearBySellHouseDomainResponse);
    }

}
