package com.toutiao.web.apiimpl.rest.plot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.MapInfo;
import com.toutiao.web.domain.query.*;
import com.toutiao.web.service.PriceTrendService;
import com.toutiao.web.service.map.MapService;
import com.toutiao.web.service.newhouse.NewHouseService;
import com.toutiao.web.service.plot.PlotService;
import com.toutiao.web.service.projhouse.ProjHouseInfoService;
import com.toutiao.web.service.rent.RentHouseService;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.HashAttributeSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/plot")
public class plot {
    @Autowired
    private PlotService plotService;

    @Autowired
    private NewHouseService newHouseService;
    @Autowired
    private ProjHouseInfoService projHouseInfoService;
    @Autowired
    private PriceTrendService priceTrendService;
    @Autowired
    private MapService mapService;
    @Autowired
    private RentHouseService rentHouseService;

    //小区详情页
    @RequestMapping("/getbyid")
    @ResponseBody
    public NashResult villageDetail(@Validated VillageRequest villageRequest) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        List villageList = plotService.findVillageByConditions(villageRequest);
        if (villageList != null && villageList.size() != 0) {
            VillageResponse village = (VillageResponse) villageList.get(0);
            objectObjectHashMap.put("village", village);

            //附近小区
            String[] latandlon = village.getLocation().split(",");
            Double lonx = Double.valueOf(latandlon[0]);
            Double laty = Double.valueOf(latandlon[1]);
            List nearvillage = plotService.GetNearByhHouseAndDistance(lonx, laty);
            objectObjectHashMap.put("nearvillage", nearvillage);

            //走势图
            Integer discId=null;
            String sdiscId = village.getAreaId();
            if (sdiscId != null){
                discId = Integer.parseInt(sdiscId);
            }

            Integer areaId=null;
            String sareaId = village.getTradingAreaId();
            if (sareaId != null){
                areaId = Integer.parseInt(sareaId);
            }

//            Map<String, Object> stringListMap = priceTrendService.priceTrendList(village.getId(),discId,areaId);
//            objectObjectHashMap.put("tradeline", stringListMap);

            //推荐小区二手房
            ProjHouseInfoQuery projHouseInfoQuery = new ProjHouseInfoQuery();
            projHouseInfoQuery.setNewcode(String.valueOf(village.getId()));
            List reViHouse = projHouseInfoService.queryProjHouseInfo(projHouseInfoQuery);
            objectObjectHashMap.put("reViHouse", reViHouse);

            //推荐小区(普租+公寓)
            RentHouseQuery rentHouseQuery = new RentHouseQuery();
            rentHouseQuery.setZuFangId(String.valueOf(villageRequest.getId()));
//            rentHouseQuery.setRentSign(0);
            Map rent = rentHouseService.queryHouseByparentId(rentHouseQuery);
            if (rent!=null){
                objectObjectHashMap.put("rent",rent);
            }


            //查询地图信息
            MapInfo mapInfo = mapService.getMapInfo(villageRequest.getId());
            JSONObject datainfo= JSON.parseObject(((PGobject) mapInfo.getDataInfo()).getValue());
            if(StringTool.isNotBlank(mapInfo)&&StringTool.isNotBlank(datainfo)){
//                objectObjectHashMap.put("mapInfo", mapInfo);
                objectObjectHashMap.put("datainfo",datainfo);
            }
            return NashResult.build(objectObjectHashMap);
        }
        return NashResult.Fail("101","没找到这个小区");
    }
}
