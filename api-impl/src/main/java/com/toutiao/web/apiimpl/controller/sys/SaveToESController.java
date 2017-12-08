package com.toutiao.web.apiimpl.controller.sys;

import com.toutiao.web.dao.entity.admin.VillageEntity;
import com.toutiao.web.service.repository.admin.SaveToESService;
import com.toutiao.web.service.repository.admin.SysVillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class SaveToESController {
    @Autowired
    private SaveToESService saveToESService;

    @Autowired
    private SysVillageService sysVillageService;

    @RequestMapping("/saveToES")
    public void setSaveToESService(String index, String type, VillageEntity village, Model model){
        try {
            saveToESService.save(index, type, village);
            VillageEntity villageEntity = sysVillageService.findVillageById(index, type, village.getId());
            if(villageEntity.equals(null)){
                model.addAttribute("message","插入失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
