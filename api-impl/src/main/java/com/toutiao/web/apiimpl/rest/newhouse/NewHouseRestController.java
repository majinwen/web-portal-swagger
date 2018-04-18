package com.toutiao.web.apiimpl.rest.newhouse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.newhouse.NewHouseListRequest;
import com.toutiao.app.api.chance.response.newhouse.NewHosueListResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseDetailResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseLayoutResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseListDominResponse;
import com.toutiao.app.domain.newhouse.NewHouseDetailDo;
import com.toutiao.app.domain.newhouse.NewHouseLayoutDo;
import com.toutiao.app.domain.newhouse.NewHouseListDo;
import com.toutiao.app.domain.newhouse.NewHouseListDomain;
import com.toutiao.app.service.newhouse.NewHouseRestService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.service.map.MapService;
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
        NewHouseListDominResponse newHouseListDominResponse = new NewHouseListDominResponse();
        NewHouseListDo newHouseListDo=new NewHouseListDo();
        BeanUtils.copyProperties(newHouseListRequest,newHouseListDo);
        NewHouseListDomain newHouseListVo=newHouseService.getNewHouseList(newHouseListDo);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(newHouseListVo.getListDoList()));
        List<NewHosueListResponse> newHosueListResponses=JSONObject.parseArray(json.toJSONString(),NewHosueListResponse.class);
        newHouseListDominResponse.setNewHosueList(newHosueListResponses);
        newHouseListDominResponse.setTotalCount(newHouseListVo.getTotalCount());
        return  NashResult.build(newHouseListDominResponse);
    }

}
