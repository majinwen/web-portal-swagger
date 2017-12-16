package com.toutiao.web.apiimpl.impl.projhouse;


import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.admin.ProjHouseInfo;
import com.toutiao.web.domain.query.ProjHouseInfoQuery;
import com.toutiao.web.service.projhouse.ProjHouseInfoService;
import com.toutiao.web.service.repository.admin.SysVillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 二手房管理
 */
@Controller
@RequestMapping("/")
public class ProjHouseInfoController {

    @Autowired
    private ProjHouseInfoService projHouseInfoService;
    @Autowired
    private SysVillageService sysVillageService;


    /**
     * 功能描述：功能描述：根据房源的id以及小区的经度，维度查询房源详情，以及附近好房信息
     *
     * @param [model, houseId, lat, lon]
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/15 11:06
     */
    @RequestMapping(value = "/queryByHouseIdandLocation/{houseId}/{lat}/{lon}")
    public String queryProjHouseByhouseIdandLocation(Model model, @PathVariable("houseId") String houseId
            , @PathVariable("lat") String lat, @PathVariable("lon") String lon) {

        //房源详情
        Map<String, Object> houseDetails = null;
        Map<String, Object> builds = null;
        houseDetails = projHouseInfoService.queryByHouseId(Integer.valueOf(houseId));
        //附近好房
        builds = projHouseInfoService.
                queryProjHouseByhouseIdandLocation(Double.valueOf(lat), Double.valueOf(lon));
        //附近小区缺少接口
        List plotList = sysVillageService.GetNearByhHouseAndDistance(Double.valueOf(lat), Double.valueOf(lon));
        if (StringTool.isNotEmpty(plotList)) {
            model.addAttribute("plotList", plotList);
        }
        if (StringTool.isNotEmpty(houseDetails)) {
            model.addAttribute("houseDetail", houseDetails.get("data_house"));
        }
        if (StringTool.isNotEmpty(builds)) {
            model.addAttribute("plot", builds.get("data_plot"));
        }
        return "esf/esf-detail";
    }

    /**
     * 功能描述：二手房列表
     *
     * @param [projHouseInfoQuery, model]
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/15 10:59
     */
    @RequestMapping("/findProjHouseInfo")
    public String searchProjHouseInfo(ProjHouseInfoQuery projHouseInfoQuery, Model model) {

//        Map<String,Object> builds =  projHouseInfoService.queryProjHouseInfo(projHouseInfoRequest);
//        model.addAttribute("builds",builds.get("data"));
//        model.addAttribute("total",builds.get("total"));
        //projHouseInfoQuery.setSubwayLineId("001");
        // projHouseInfoQuery.setSubwayStationId("001");
        //projHouseInfoQuery.setHouseBusinessName("朝阳");
        //projHouseInfoQuery.setAreaId("12");
        //projHouseInfoQuery.setPrice("0,100");
        //projHouseInfoQuery.setHouseTypeId("1,2");
        //projHouseInfoQuery.setHouseAreaId("0,60,60,200");
        List builds = projHouseInfoService.queryProjHouseInfo(projHouseInfoQuery);
        if (StringTool.isNotEmpty(builds)&& builds.size()>0) {
            model.addAttribute("builds", builds);
        }
        return "esf/esf-list";

    }

    /**
     * 功能描述：往es服务器中插入数据
     *
     * @param [ProjHouseInfo]
     * @return com.toutiao.web.common.restmodel.NashResult
     * @author zhw
     * @date 2017/12/15 18:30
     */
    @RequestMapping("/saveProjHouseInfoToEs")
    public NashResult saveProjHouseInfoToEs(ProjHouseInfo projHouseInfo) {
        boolean b = projHouseInfoService.saveProjHouseInfo(projHouseInfo);
        if (b) {
            return NashResult.build("保存成功！");
        }
        return NashResult.Fail("保存失败！");
    }
    /**
     *
     * 功能描述：通过索搜框查找相应的数据
     * @author zhw
     * @date 2017/12/16 9:46
     * @param [text]
     * @return java.lang.String
     */
    @RequestMapping("queryBySearchBox")
    public String queryHouseInfoBySearchBox(Model model,@RequestParam("text") String text) {

        List queryBySearchBox = projHouseInfoService.queryBySearchBox(text);

        if (StringTool.isNotEmpty(queryBySearchBox) && queryBySearchBox.size()>0){

            model.addAttribute("builds",queryBySearchBox);
        }else{
            model.addAttribute("message","没有该相应的数据信息");
        }
        return "esf/esf-list";
    }


}
