package com.toutiao.web.apiimpl.rest.newhouse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.newhouse.NewHouseLayoutRequest;
import com.toutiao.app.api.chance.response.newhouse.NewHouseLayoutCountResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseLayoutResponse;
import com.toutiao.app.domain.newhouse.NewHouseLayoutCountDo;
import com.toutiao.app.domain.newhouse.NewHouseLayoutDo;
import com.toutiao.app.service.newhouse.NewHouseLayoutService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rest/layout")
public class NewHouseLayoutRestController {


    @Autowired
    private NewHouseLayoutService newHouseLayoutService;



    /**
     * 根据新房id获取户型数量信息
     *
     */
    @ResponseBody
    @RequestMapping(value = "/getLayoutCountByNewHouseId")
    public NashResult getLayoutCountByNewHouseId (@Validated NewHouseLayoutRequest newHouseLayoutRequest) {
        Integer newHouseId = newHouseLayoutRequest.getNewHouseId();
        List<NewHouseLayoutCountDo> newHouseLayoutCountDoList = newHouseLayoutService.getNewHouseLayoutByNewHouseId(newHouseId);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(newHouseLayoutCountDoList));
        List<NewHouseLayoutCountResponse> newHouseLayoutCountResponses = JSONObject.parseArray(json.toJSONString(),NewHouseLayoutCountResponse.class);
        return NashResult.build(newHouseLayoutCountResponses);
    }

    /**
     * 根据新房id获取该户型下的户型列表
     * @param newHouseLayoutRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getLayoutByNewHouseId")
    public NashResult getNewHouseLayoutByNewCode (@Validated(First.class) NewHouseLayoutRequest newHouseLayoutRequest) {
        Integer newHouseId = newHouseLayoutRequest.getNewHouseId();
        Integer room = newHouseLayoutRequest.getRoom();
        List<NewHouseLayoutDo> newHouseLayoutDoList = newHouseLayoutService.getNewHouseLayoutList(newHouseId,room);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(newHouseLayoutDoList));
        List<NewHouseLayoutResponse> newHouseLayoutResponses = JSONObject.parseArray(json.toJSONString(),NewHouseLayoutResponse.class);
        return NashResult.build(newHouseLayoutResponses);
    }

}
