package com.toutiao.web.apiimpl.impl.projhouse;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.PriceTrend;
import com.toutiao.web.domain.query.ProjHouseInfoQuery;
import com.toutiao.web.domain.query.ProjHouseInfoResponse;
import com.toutiao.web.domain.query.VillageRequest;
import com.toutiao.web.service.plot.PlotService;
import com.toutiao.web.service.projhouse.ProjHouseInfoService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
    private PlotService plotService;


    /**
     * 功能描述：功能描述：根据房源的id以及小区的经度，维度查询房源详情，以及附近好房信息
     * <p>
     *
     * @param [model, houseId, lat, lon]
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/15 11:06
     */
    @RequestMapping(value = "/queryByHouseIdandLocation/{houseId}")
    public String queryProjHouseByhouseIdandLocation(Model model, @PathVariable("houseId") Integer houseId) {
        //房源详情
        Map<String, Object> houseDetails = projHouseInfoService.queryByHouseId(houseId);
        if (StringTool.isNotBlank(houseDetails)) {
            model.addAttribute("houseDetail", houseDetails.get("data_house"));
            ProjHouseInfoResponse data_house = (ProjHouseInfoResponse) houseDetails.get("data_house");
            //附近好房
            List houseInfo = projHouseInfoService.queryProjHouseByhouseIdandLocation(houseId.toString(),Double.valueOf(data_house.getLon()), Double.valueOf(data_house.getLat()));
            if (StringTool.isNotEmpty(houseInfo)) {
                model.addAttribute("plot", houseInfo);
            }
            //附近小区缺少接口
            List plotList = plotService.GetNearByhHouseAndDistance(Double.valueOf(data_house.getLon()), Double.valueOf(data_house.getLat()));
            if (StringTool.isNotEmpty(plotList)) {
                model.addAttribute("plotList", plotList);
            }
        }else{
            //跳转到404页
            return "";
        }

        return "esf/esf-detail";
    }

    /**
     * 二手房配套地图
     * @return
     */
    @RequestMapping("/getProjHouseMapDetail")
    public String getNewHouseMapDetail(ProjHouseInfoQuery projHouseInfoQuery, Model model) {


        List list = projHouseInfoService.queryProjHouseInfo(projHouseInfoQuery);

        if (list != null && list.size() > 0) {
            ProjHouseInfoResponse build = (ProjHouseInfoResponse) list.get(0);
            build.setLocation(build.getHousePlotLocation());

            model.addAttribute("build", build);
            return "map";
        }
        ProjHouseInfoResponse build = new ProjHouseInfoResponse();
        build.setNewcode(Integer.valueOf(projHouseInfoQuery.getNewcode()));
        build.setLocation("39.913329,116.382001");

        model.addAttribute("build", build);

        return "map";

    }




    /**
     * 功能描述：二手房分页
     */
    @RequestMapping("/esfHousePageSearch")
    @ResponseBody
    public NashResult esfHousePageSearch(ProjHouseInfoQuery projHouseInfoQuery) {

        List builds = projHouseInfoService.queryProjHouseInfo(projHouseInfoQuery);

        return  NashResult.build(builds);

    }

    /**
     * 功能描述：二手房列表
     * <p>
     * //     * @param [projHouseInfoQuery, model]
     *
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/15 10:59
     */
    @RequestMapping("/findProjHouseInfo")
    public String searchProjHouseInfo(ProjHouseInfoQuery projHouseInfoQuery, Model model) {
        List builds = projHouseInfoService.queryProjHouseInfo(projHouseInfoQuery);

        if (StringTool.isNotEmpty(builds) && builds.size() > 0) {
            model.addAttribute("builds", builds);
        }
        if (projHouseInfoQuery.getSort() != null) {
            model.addAttribute("sort", projHouseInfoQuery.getSort());
        } else {
            model.addAttribute("sort", 0);
        }
//        model.addAttribute("searchType", "projhouse");
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
//    @RequestMapping("/saveProjHouseInfoToEs")
//    public NashResult saveProjHouseInfoToEs(ProjHouseInfo projHouseInfo) {
//        boolean b = projHouseInfoService.saveProjHouseInfo(projHouseInfo);
//        if (b) {
//            return NashResult.build("保存成功！");
//        }
//        return NashResult.Fail("保存失败！");
//    }

    /**
     * 功能描述：通过索搜框查找相应的数据
     *
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/16 9:46
     * //     * @param [text]
     */
    @RequestMapping("queryBySearchBox")
    public String queryHouseInfoBySearchBox(Model model, @RequestParam("text") String text) {

        List queryBySearchBox = projHouseInfoService.queryBySearchBox(text);

        if (StringTool.isNotEmpty(queryBySearchBox) && queryBySearchBox.size() > 0) {

            model.addAttribute("builds", queryBySearchBox);
            model.addAttribute("text", text);
//            model.addAttribute("searchType", "projhouse");

        } else {
            model.addAttribute("message", "没有该相应的数据信息");
            model.addAttribute("text", text);
//            model.addAttribute("searchType", "projhouse");
        }
        return "esf/esf-list";
    }


}
