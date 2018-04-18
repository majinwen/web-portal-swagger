package com.toutiao.web.apiimpl.rest.newhouse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.newhouse.NewHouseDynamicRequest;
import com.toutiao.app.api.chance.request.newhouse.NewHouseListRequest;
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
    public NashResult getNewHouseDetailByNewCode(@RequestParam(value = "newcode",required =true) Integer newcode)
    {
        NewHouseDetailResponse newHouseDetailResponse= new NewHouseDetailResponse();
        NewHouseDetailDo newHouseDetailDo= newHouseService.getNewHouseBulidByNewcode(newcode);
        BeanUtils.copyProperties(newHouseDetailDo,newHouseDetailResponse);
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
        NewHouseListDo newHouseListDo=new NewHouseListDo();
        BeanUtils.copyProperties(newHouseListRequest,newHouseListDo);
        NewHouseListDomain newHouseListVo=newHouseService.getNewHouseList(newHouseListDo);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(newHouseListVo.getListDoList()));
        List<NewHouseListResponse> newHouseListResponses=JSONObject.parseArray(json.toJSONString(),NewHouseListResponse.class);
        newHouseListDomainResponse.setNewHosueList(newHouseListResponses);
        newHouseListDomainResponse.setTotalCount(newHouseListVo.getTotalCount());
        return  NashResult.build(newHouseListDomainResponse);
    }


    /**
     * 根据newcode获取新房动态
     */
    @ResponseBody
    @RequestMapping(value = "getNewHouseDynamic",method = RequestMethod.GET)
    public  NashResult getNewHouseDynamicByNewcode(@Validated NewHouseDynamicRequest newHouseDynamicRequest)
    {
        NewHouseDynamicDo newHouseDynamicDo =new NewHouseDynamicDo();
        BeanUtils.copyProperties(newHouseDynamicRequest,newHouseDynamicDo);
        List<NewHouseDynamicDo>   newHouseDynamicDoList= newHouseService.getNewHouseDynamicByNewCode(newHouseDynamicDo);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(newHouseDynamicDoList));
         List<NewHouseDynamicResponse> newHouseDynamicResponses=JSONObject.parseArray(json.toJSONString(), NewHouseDynamicResponse.class);
        return  NashResult.build(newHouseDynamicResponses);
    }

}
