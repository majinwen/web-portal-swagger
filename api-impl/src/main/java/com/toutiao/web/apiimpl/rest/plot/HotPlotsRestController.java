package com.toutiao.web.apiimpl.rest.plot;

import com.toutiao.app.api.chance.response.hotplot.HotPlotListResponse;
import com.toutiao.app.domain.hotplot.SearchHotProj;
import com.toutiao.app.domain.hotplot.SearchHotProjDo;
import com.toutiao.app.domain.hotplot.SearchHotProjDomain;
import com.toutiao.app.service.plot.HotPlotsRestService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-22
 * Time:   17:16
 * Theme:
 */

@RestController
@RequestMapping("/rest/hotplot")
public class HotPlotsRestController {

    @Autowired
    private HotPlotsRestService hotPlotsRestService;

    @RequestMapping(value = "/getHotPlotByCityId")
    @ResponseBody
    public NashResult getSuggestByKeyword(){
        String city = "";
        if(StringTool.isNotEmpty(CityUtils.getCity())){
            city = CityUtils.returnCityId(CityUtils.getCity()).toString();
        }
        SearchHotProjDomain searchHotProjDomain = hotPlotsRestService.getHotPlotsByCityId(city);
        HotPlotListResponse hotPlotListResponse = new HotPlotListResponse();
        BeanUtils.copyProperties(searchHotProjDomain,hotPlotListResponse);

        return NashResult.build(searchHotProjDomain);
    }


}
