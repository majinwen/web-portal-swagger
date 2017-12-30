package com.toutiao.web.apiimpl.impl.newhouse;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.entity.officeweb.PriceTrend;
import com.toutiao.web.domain.query.NewHouseQuery;
import com.toutiao.web.service.PriceTrendService;
import com.toutiao.web.service.newhouse.NewHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/{citypath}")
public class NewHouseController {

    @Autowired
    private NewHouseService newHouseService;
    @Autowired
    private PriceTrendService priceTrendService;

    /**
     * 新房首页
     * @return
     */
    @RequestMapping("/xinfang")
    public String index(NewHouseQuery newHouseQuery, Model model) {
         newHouseQuery.setSort(0);
         newHouseQuery.setPageNum(1);
         newHouseQuery.setPageSize(4);
         Map<String,Object> builds = newHouseService.getNewHouse(newHouseQuery);
         model.addAttribute("newbuilds",builds);
         return "newhouse/new-index";
    }

    /**
     * 新房列表
     * @param newHouseQuery
     * @param model
     * @return
     */
    @RequestMapping("/loupan")
    public String searchNewHouse(NewHouseQuery newHouseQuery, Model model){
        Map<String,Object> builds =  newHouseService.getNewHouse(newHouseQuery);
       ArrayList<HashMap<String,Object>> build= (ArrayList<HashMap<String, Object>>) builds.get("data");
        model.addAttribute("builds",build);
        model.addAttribute("total",builds.get("total"));
        model.addAttribute("newHouseQuery",newHouseQuery);
        if (newHouseQuery.getSort()!=null){
            model.addAttribute("sort",newHouseQuery.getSort());
        }else {
            model.addAttribute("sort",0);
        }
        return "newhouse/new-list";
    }

    @RequestMapping(value = "/loupan",produces="application/json")
    @ResponseBody
    public NashResult searchNewHouse(NewHouseQuery newHouseQuery){
        Map<String,Object> builds =  newHouseService.getNewHouse(newHouseQuery);
        ArrayList<HashMap<String,Object>> build= (ArrayList<HashMap<String, Object>>) builds.get("data");

        for (int i=0;i<build.size();i++) {
            HashMap<String,Object> itemMap = build.get(i);
            String imginfo= (String) itemMap.get("building_imgs");
              if (StringUtil.isNotNullString(imginfo)){
                 String [] imgs= imginfo.split(",");
                 itemMap.put("building_imgs",imgs[0]);
                  build.set(i,itemMap);
              }
        }
        NashResult.build(build);
        return NashResult.build(build);
    }

//    /**
//     * 搜索新房
//     *
//     * */
//    @RequestMapping("/searchNewHouseByKey")
//    public String searchNewHouseByKey(NewHouseQuery newHouseQuery, Model model){
//        ArrayList<HashMap<String,Object>> build= (ArrayList<HashMap<String, Object>>) newHouseService.searchNewHouse(newHouseQuery);
//        model.addAttribute("builds",build);
//        return "newhouse/new-list";
//    }

    /**
     * 楼盘详情信息
     * @param buildingId
     * @param model
     * @return
     */
    @RequestMapping("/loupan/{id}.html")
    public String getNewHouseDetails(@PathVariable("id") Integer buildingId, Model model){
        Map<String,Object> details = newHouseService.getNewHouseDetails(buildingId);
        List<String>dateList= DateUtil.oneYearList();

        String detailBuild = (String) details.get("build");
        JSONObject build=JSON.parseObject(detailBuild);
        Integer discId = null;
       if ( build.getInteger("district_id")!=null){
           discId = build.getInteger("district_id");
       }

        Integer areaId = null;
        Map<String ,Object> priceTrendList = priceTrendService.priceTrendList(buildingId,discId,areaId);

        model.addAttribute("build",build);
        model.addAttribute("layout", details.get("layout"));
        model.addAttribute("nearbybuild",details.get("nearbybuild"));
        model.addAttribute("tradeline",priceTrendList);
        return "newhouse/new-detail";

    }

//    /**
//     * 楼盘户型详情
//     * @param buildingId
//     * @param tags
//     * @param model
//     * @return
//     */
//    @RequestMapping("/getNewHouseLayoutDetails")
//    public String getNewHouseLayoutDetails(@RequestParam("id") Integer buildingId,@RequestParam("tags") Integer tags, Model model){
//        Map<String,Object> details = newHouseService.getNewHouseLayoutDetails(buildingId,tags);
//        model.addAttribute("layoutDetails", details.get("layouts"));
//        return "";
//
//    }

    /**
     * 根据楼盘计算不同户型数量
     * @param buildingId
     * @param model
     * @return
     */
    @RequestMapping("/loupan/{loupanid}/huxing.html")
    public String getNewHouseLayoutCountByRoom(@PathVariable("loupanid") Integer buildingId,@RequestParam("tags") Integer tags,  Model model){
        List<Map<String,Object>> room = newHouseService.getNewHouseLayoutCountByRoom(buildingId);
        Map<String,Object> details = newHouseService.getNewHouseLayoutDetails(buildingId,tags);
        int rs = 0;
        if(room.size() > 0){
            for(int i=0;i<room.size();i++){
                rs = rs+ Integer.valueOf(String.valueOf(room.get(i).get("count")));
            }
        }
        model.addAttribute("layoutDetails", details.get("layouts"));
        model.addAttribute("room",room);
        model.addAttribute("bid",buildingId);
        model.addAttribute("tags",tags);
        model.addAttribute("roomcount",rs);
        return "newhouse/new-house-type";

    }


    /**
     * 新房配套地图
     * @return
     */
    @RequestMapping("/loupan/{id}/map.html")
    public String getNewHouseMapDetail(@PathVariable("id") Integer buildingId, Model model){
        Map<String,Object> details = newHouseService.getNewHouseDetails(buildingId);

        String detailBuild = (String) details.get("build");
        JSONObject build=JSON.parseObject(detailBuild);
        model.addAttribute("build",build);

        return "map";

    }

    /**
     * 楼盘全部描述
     * @param buildingId
     * @param model
     * @return
     */
    @RequestMapping("/loupan/{id}/desc.html")
    public String getNewHouseDiscript(@PathVariable("id") Integer buildingId, Model model){
        List<Map<String,Object>> discripts=newHouseService.getNewHouseDiscript(buildingId);
        model.addAttribute("discript",discripts);
        return "newhouse/new-parameter";
    }
}
