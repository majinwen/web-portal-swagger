package com.toutiao.web.apiimpl.rest.plot;

import com.toutiao.app.api.chance.request.plot.PlotAroundPlot;
import com.toutiao.app.api.chance.request.plot.PlotDetailsRequest;
import com.toutiao.app.api.chance.response.plot.PlotDetailsResponse;
import com.toutiao.app.domain.MapInfo;
import com.toutiao.app.domain.Plot.PlotDetailsDo;
import com.toutiao.app.service.plot.AppPlotService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/rest/plot")
public class AppPlot {
    @Autowired
    private AppPlotService appPlotService;

    /**
     * 获取小区详情信息
     * @param plotDetailsRequest
     * @return
     */
    @RequestMapping("/getPlotDetailByPlotId")
    @ResponseBody
    public NashResult getPlotDetailByPlotId(@Validated PlotDetailsRequest plotDetailsRequest) {
        PlotDetailsDo plotDetailsDo = appPlotService.queryPlotDetailByPlotId(plotDetailsRequest.getPlotId());
        if (plotDetailsDo!=null){
            PlotDetailsResponse plotDetailsResponse = new PlotDetailsResponse();
            BeanUtils.copyProperties(plotDetailsDo,plotDetailsResponse);
            return NashResult.build(plotDetailsResponse);
        }
        return NashResult.Fail("101","未找到该小区");
    }

    /**
     * 获取小区周边信息
     * @param plotDetailsRequest
     * @return
     */
    @RequestMapping("/getPlotAroundByPlotId")
    @ResponseBody
    public NashResult getPlotAroundByPlotId(@Validated PlotDetailsRequest plotDetailsRequest){
        MapInfo mapInfo = appPlotService.queryPlotMapInfo(plotDetailsRequest.getPlotId());
        if (mapInfo!=null){
            return NashResult.build(mapInfo);
        }
        return NashResult.Fail("102","未找到该小区周边信息");
    }

    /**
     * 获取周边小区
     * @param plotAroundPlot
     * @return
     */
    @RequestMapping("/getAroundPlotByLocation")
    @ResponseBody
    public NashResult getPlotAroundByLocation(@Validated PlotAroundPlot plotAroundPlot){
        List<PlotDetailsResponse> results = new ArrayList<>();
        List<PlotDetailsDo> plotDetailsDos = appPlotService.queryAroundPlotByLocation(plotAroundPlot.getLat(), plotAroundPlot.getLon(),plotAroundPlot.getPlotId());
        if (plotDetailsDos!=null&&plotDetailsDos.size()>0){
            for (int i=0;i<plotDetailsDos.size();i++ ){
                PlotDetailsResponse plotDetailsResponse = new PlotDetailsResponse();
                BeanUtils.copyProperties(plotDetailsDos.get(i),plotDetailsResponse);
                results.add(plotDetailsResponse);
            }
            return NashResult.build(results);
        }
        return NashResult.Fail("103","未找到周边小区");
    }


}
