package com.toutiao.web.apiimpl.controller.sys;

import com.toutiao.web.dao.entity.admin.VillageEntity;
import com.toutiao.web.service.repository.admin.SaveToESService;
import com.toutiao.web.service.repository.admin.SysVillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class SaveToESController {
    @Autowired
    private SaveToESService saveToESService;

    @RequestMapping("/saveToES")
    public void setSaveToESService(String index, String type, VillageEntity village, Model model){
        VillageEntity village1 = new VillageEntity();
        village1.setId(003);
        village1.setAreaId("003");
        village1.setArea("丰台");
        village1.setAreaNameId("003");
        village1.setAreaName("丰台门");
        String[] mt = {"005","006"};
        village1.setMetroStationId(mt);
        String[] mtStation ={"丰台东","丰台"};
        String[] li ={"005","006"};
        String[] liName ={"5号线","6号线"};
        village1.setSubwayLineId(li);
        String[] la = {"005","006"};
        village1.setLabelId(la);
        String[] laName = {"标签5","标签6"};
        village1.setAvgPrice(79999);
        village1.setAge(18);
        village1.setElevator("有");
        Double[] loca = {30.0,30.0};
        village1.setLocation(loca);
        village1.setLevel(2);

        Boolean flag = null;
        try {
            flag = saveToESService.save(index, type, village1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("flag",flag);
    }
}
