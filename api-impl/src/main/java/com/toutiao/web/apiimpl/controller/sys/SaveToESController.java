package com.toutiao.web.apiimpl.controller.sys;

import com.toutiao.web.dao.entity.admin.ProjHouseInfo;
import com.toutiao.web.dao.entity.admin.ProjHouseInfoES;
import com.toutiao.web.dao.entity.admin.VillageEntityES;
import com.toutiao.web.service.plot.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
public class SaveToESController {
    @Autowired
    private PlotService plotService;

    @RequestMapping("/saveParentToES")
    @ResponseBody
    public void saveParentToES(VillageEntityES village, Model model){
        VillageEntityES village1 = new VillageEntityES();
        village1.setId(1);
        village1.setAreaId("002");
        village1.setArea("丰台11111111");
        String[] mt = {"001","002"};
        village1.setMetroStationId(mt);
        String[] mtStation ={"朝阳东","朝阳"};
        village1.setMetroStation(mtStation);
        String[] li ={"001","002"};
        village1.setSubwayLineId(li);
        String[] liName ={"1号线","2号线"};
        village1.setSubwayLine(liName);
        Map<String,String> map = new HashMap<String,String>();
        map.put(village1.getSubwayLineId()[0]+"_"+village1.getMetroStationId()[0],"100000");
        map.put(village1.getSubwayLineId()[1]+"_"+village1.getMetroStationId()[1],"200000");
        village1.setMetroWithPlotsDistance(map);
        String[] la = {"001","002"};
        village1.setLabelId(la);
        String[] laName = {"标签1","标签2"};
        village1.setLabel(laName);
        village1.setAvgPrice(333333);
        village1.setAge(16);
        village1.setElevator("有");
//        Double[] loca = {10.0,10.0};
//        village1.setLocation(loca);
        village1.setLevel(1);
        village1.setVersion(32);
          village1.setAvgPrice(5555555);
          village1.setYopr("5555555555");
        plotService.saveParent(village);
    }

    @RequestMapping("/saveChildToES")
    @ResponseBody
    public void saveChildToES(ProjHouseInfoES projHouseInfo, Model model){
        ProjHouseInfo projHouseInfo1 = new ProjHouseInfo();
        projHouseInfo1.setHouseId(1);
        projHouseInfo1.setHouseArea("66666");
//        projHouseInfo1.setHousePlotId(1);
        plotService.saveChild(projHouseInfo);
    }

}
