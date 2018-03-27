package com.toutiao.web.apiimpl.rest.plot;

import com.toutiao.app.api.chance.request.plot.PlotDetailsRequest;
import com.toutiao.app.api.chance.response.plot.PlotDetailsResponse;
import com.toutiao.app.domain.Plot.PlotDetailsDo;
import com.toutiao.app.service.plot.AppPlotService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/plot")
public class AppPlot {
    @Autowired
    private AppPlotService appPlotService;

    //小区详情页
    @RequestMapping("/getByPlotId")
    @ResponseBody
    public NashResult getByPlotId(@Validated PlotDetailsRequest plotDetailsRequest) {
        PlotDetailsResponse plotDetailsResponse = new PlotDetailsResponse();
        PlotDetailsDo plotDetailsDo = appPlotService.queryPlotDetailByPlotId(plotDetailsRequest.getPlotId());
        BeanUtils.copyProperties(plotDetailsDo,plotDetailsResponse);
        return NashResult.build(plotDetailsResponse);
    }
}
