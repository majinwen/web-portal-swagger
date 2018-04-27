package com.toutiao.web.apiimpl.rest.homepage;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.response.homepage.HomePageEsfResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseListResponse;
import com.toutiao.app.domain.homepage.HomePageEsfDo;
import com.toutiao.app.service.homepage.HomePageRestService;
import com.toutiao.web.common.restmodel.InvokeResult;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/homePage")
public class HomePageRestController {

    @Autowired
    private HomePageRestService homePageRestService;


    @RequestMapping(value = "getHomePageEsf",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getHomePageEsf()
    {
       List<HomePageEsfDo> homePageEsfDos= homePageRestService.getHomePageEsf();
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(homePageEsfDos));
        List<HomePageEsfResponse> homePageEsfResponseList= JSONObject.parseArray(json.toJSONString(),HomePageEsfResponse.class);
        return  NashResult.build(homePageEsfResponseList);
    }
}
