package com.toutiao.web.apiimpl.rest.advertisement;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.advertisement.AdCommunity;
import com.toutiao.app.api.chance.response.plot.PlotTop50ListResponse;
import com.toutiao.app.api.chance.response.plot.PlotTop50Response;
import com.toutiao.app.domain.plot.PlotTop50Do;
import com.toutiao.app.service.advertisement.AdCommunityRestService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.city.CityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-11-02
 * Time:   18:07
 * Theme:
 */


@RestController
@RequestMapping("/rest/ad/community")
public class AdCommunityRestController {

    @Autowired
    private AdCommunityRestService adCommunityRestService;


    /**
     * 必看社区
     *
     * @param adCommunity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAdExcellentCommunityByIds", method = RequestMethod.GET)
    public PlotTop50ListResponse getAdExcellentCommunityByIds(@Validated AdCommunity adCommunity) {
        List<PlotTop50Do> plotTop50Dos = adCommunityRestService.getExcellentCommunityByIds(adCommunity.getCommunityIds(), CityUtils.getCity());
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(plotTop50Dos));
        List<PlotTop50Response> plotTop50Responses = JSONObject.parseArray(json.toJSONString(), PlotTop50Response.class);
        PlotTop50ListResponse plotTop50ListResponse = new PlotTop50ListResponse();
        plotTop50ListResponse.setPlotTop50ResponseList(plotTop50Responses);
        plotTop50ListResponse.setTotalNum(plotTop50Responses.size());
        return plotTop50ListResponse;
    }
}
