package com.toutiao.web.apiimpl.rest.Appnewhouse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.response.newhouse.NewHouseDetailResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseLayoutResponse;
import com.toutiao.app.domain.newhouse.NewHouseDetailDo;
import com.toutiao.app.domain.newhouse.NewHouseLayoutDo;
import com.toutiao.app.service.newhouse.AppNewHouseService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/newhouse")
public class AppNewHouse {
    @Autowired
    private AppNewHouseService newHouseService;

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
     * 根据newcode获取户型信息
     */
    @ResponseBody
    @RequestMapping(value = "/getLayoutByNewCode",method = RequestMethod.GET)
    public NashResult getNewHouseLayoutByNewCode (@RequestParam(value = "newcode",required = true) Integer newcode)
    {
        List<NewHouseLayoutDo> newHouseLayoutDos=newHouseService.getNewHouseLayoutByNewcode(newcode);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(newHouseLayoutDos));
        List<NewHouseLayoutResponse> newHouseLayoutResponses = JSONObject.parseArray(json.toJSONString(),NewHouseLayoutResponse.class);
        return NashResult.build(newHouseLayoutResponses);
    }


}
