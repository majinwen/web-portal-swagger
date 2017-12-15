package com.toutiao.web.apiimpl.controller;


import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.dao.entity.admin.ProjHouseInfo;
import com.toutiao.web.domain.query.ProjHouseInfoQuery;
import com.toutiao.web.service.repository.admin.ProjHouseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 二手房管理
 */
@RestController
@RequestMapping("/")
public class ProjHouseInfoController {

    @Autowired
    private ProjHouseInfoService projHouseInfoService;

    private static Logger logger = LoggerFactory.getLogger(ProjHouseInfoController.class);

    /**
     * 根据范围查找数据
     *
     * @param projHouseInfo
     * @return
     */
    @RequestMapping(value = "/queryInfoByRang")
    @ResponseBody
    public NashResult queryInfoByRang(ProjHouseInfo projHouseInfo) {
        if (logger.isDebugEnabled()) {
            logger.debug(" in projHouseInfoService.queryProjHouseInfoByRang(){} - start ");
        }
        List<ProjHouseInfo> list = projHouseInfoService.
                queryProjHouseInfoByRang("tes001", "proj_house", "houseTotalPrice", 900, 920);
        if (logger.isDebugEnabled()) {
            logger.debug(" in projHouseInfoService.queryProjHouseInfoByRang(){} - end ");
        }
        return NashResult.build(list);
    }

    /**
     * 二手房列表
     * @param ProjHouseInfoQuery
     * @param model
     * @return
     */
    @RequestMapping("/findProjHouseInfo")
    public String searchNewHouse(ProjHouseInfoQuery ProjHouseInfoQuery, Model model){
//        Map<String,Object> builds =  projHouseInfoService.queryProjHouseInfo(projHouseInfoRequest);
//        model.addAttribute("builds",builds.get("data"));
//        model.addAttribute("total",builds.get("total"));
        Map<String,Object> builds =  projHouseInfoService.queryProjHouseInfo(ProjHouseInfoQuery);
        model.addAttribute("builds",builds.get("data"));
        model.addAttribute("total",builds.get("total"));
        return "projHouse-list";

    }
}
