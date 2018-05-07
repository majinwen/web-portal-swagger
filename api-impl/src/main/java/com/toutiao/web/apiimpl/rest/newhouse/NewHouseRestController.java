package com.toutiao.web.apiimpl.rest.newhouse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.newhouse.NewHouseDetailsRequest;
import com.toutiao.app.api.chance.request.newhouse.NewHouseDynamicRequest;
import com.toutiao.app.api.chance.request.newhouse.NewHouseListRequest;
import com.toutiao.app.api.chance.request.newhouse.NewHouseTrafficRequest;
import com.toutiao.app.api.chance.response.newhouse.*;
import com.toutiao.app.domain.newhouse.*;
import com.toutiao.app.service.newhouse.NewHouseRestService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/newhouse")
public class NewHouseRestController {
    @Autowired
    private NewHouseRestService newHouseService;

    /**
     * 根据newcode获取新房数据
     */
    @ResponseBody
    @RequestMapping(value = "/getDetailByNewCode",method = RequestMethod.GET)
    public NashResult getNewHouseDetailByNewCode(@Validated NewHouseDetailsRequest newHouseDetailsRequest)
    {
        NewHouseDetailDo newHouseDetailDo= newHouseService.getNewHouseBuildByNewCode(newHouseDetailsRequest.getNewCode());
        NewHouseDetailResponse newHouseDetailResponse = JSON.parseObject(JSON.toJSONString(newHouseDetailDo), NewHouseDetailResponse.class);
        return NashResult.build(newHouseDetailResponse);
    }


    /**
     * 获取新房列表页
     */
    @ResponseBody
    @RequestMapping(value = "/getNewHouseList",method =RequestMethod.GET)
    public  NashResult getNewHouseList(@Validated NewHouseListRequest newHouseListRequest)
    {
        NewHouseListDomainResponse newHouseListDomainResponse = new NewHouseListDomainResponse();
        NewHouseDoQuery newHouseDoQuery=new NewHouseDoQuery();
        BeanUtils.copyProperties(newHouseListRequest,newHouseDoQuery);
        NewHouseListDomain newHouseListVo = newHouseService.getNewHouseList(newHouseDoQuery);
        BeanUtils.copyProperties(newHouseListVo,newHouseListDomainResponse);
        return  NashResult.build(newHouseListDomainResponse);
    }


    /**
     * 根据newcode获取新房动态
     */
    @ResponseBody
    @RequestMapping(value = "/getNewHouseDynamic",method = RequestMethod.GET)
    public  NashResult getNewHouseDynamicByNewCode(@Validated NewHouseDynamicRequest newHouseDynamicRequest)
    {
        NewHouseDynamicDoQuery newHouseDynamicDoQuery =new NewHouseDynamicDoQuery();
        BeanUtils.copyProperties(newHouseDynamicRequest,newHouseDynamicDoQuery);
        List<NewHouseDynamicDo>   newHouseDynamicDoList= newHouseService.getNewHouseDynamicByNewCode(newHouseDynamicDoQuery);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(newHouseDynamicDoList));
        List<NewHouseDynamicResponse> newHouseDynamicResponses=JSONObject.parseArray(json.toJSONString(), NewHouseDynamicResponse.class);
        return  NashResult.build(newHouseDynamicResponses);
    }


    /**
     * 根据newcode获取新房交通信息
     */
    @ResponseBody
    @RequestMapping(value = "getNewHouseTraffic",method = RequestMethod.GET)
    public  NashResult getNewHouseTraffic(@Validated NewHouseTrafficRequest newHouseTrafficRequest)
    {
        NewHouseTrafficResponse newHouseTrafficResponse =new   NewHouseTrafficResponse();
        NewHouseTrafficDo newHouseTrafficDo=newHouseService.getNewHouseTrafficByNewCode(newHouseTrafficRequest.getNewCode());
        BeanUtils.copyProperties(newHouseTrafficDo,newHouseTrafficResponse);
        return  NashResult.build(newHouseTrafficResponse);
    }


}
