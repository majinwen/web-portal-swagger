package com.toutiao.appV2.apiimpl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.api.chance.request.plot.PlotListRequest;
import com.toutiao.app.api.chance.response.plot.PlotListResponse;
import com.toutiao.app.domain.plot.PlotListDo;
import com.toutiao.app.domain.plot.PlotListDoQuery;
import com.toutiao.app.service.plot.TestService;
import com.toutiao.web.common.util.city.CityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {


    @Autowired
    private TestService testService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        try {
            testService.search();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 根据条件获取小区列表
     * @param plotListRequest
     * @return
     */
    @RequestMapping(value = "/getPlotListByRequirementTest",method = RequestMethod.GET)
    @ResponseBody
    public PlotListResponse getPlotListByRequirement(@Validated PlotListRequest plotListRequest){
        PlotListDoQuery plotListDoQuery = new PlotListDoQuery();
        BeanUtils.copyProperties(plotListRequest, plotListDoQuery);
        PlotListDo plotListDo = null;
        try {
            plotListDo = testService.queryPlotListByRequirement(plotListDoQuery, CityUtils.getCity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.parseObject(JSON.toJSONString(plotListDo), PlotListResponse.class);
    }

}
