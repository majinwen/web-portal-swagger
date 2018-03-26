package com.toutiao.web.apiimpl.rest.sellhouse;


import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.app.api.chance.request.sellhouse.SellHouseDetailsRequest;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.service.plot.PlotService;
import com.toutiao.web.service.projhouse.ProjHouseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/rest/esf")
public class SellHouseRestController {


    @Autowired
    private ProjHouseInfoService projHouseInfoService;
    @Autowired
    private PlotService plotService;
    @Autowired
    private SellHouseService sellHouseService;


    /**
     *  二手房详情
     * @param sellHouseDetailsRequest
     * @return
     */
    @RequestMapping("/getByHouseId")
    @ResponseBody
    public NashResult getByHouseId(@Validated SellHouseDetailsRequest sellHouseDetailsRequest) {
        Map<Object, Object> esfMap = new HashMap<>();


        System.out.println(sellHouseDetailsRequest.getHouseId());
        int sss = sellHouseService.queryNearByProjHouseInfo(sellHouseDetailsRequest.getHouseId());



        return NashResult.build(sss);
    }


//    //小区详情页
//    @RequestMapping("/getByHouseId")
//    @ResponseBody
//    public NashResult getByHouseId(@RequestParam("houseId") Integer houseId) {
//        Map<Object, Object> esfMap = new HashMap<>();
//        //房源详情
//        Map<String, Object> houseDetails = projHouseInfoService.queryByHouseId(houseId);
//        if (StringTool.isNotBlank(houseDetails)) {
//            esfMap.put("houseId",houseId);
//            esfMap.put("houseDetail", houseDetails.get("data_house"));
//            ProjHouseInfoResponse data_house = (ProjHouseInfoResponse) houseDetails.get("data_house");
//            VillageRequest villageRequest = new VillageRequest();
//            villageRequest.setId(data_house.getNewcode());
//            List village = plotService.findVillageByConditions(villageRequest);
//            esfMap.put("village",village.get(0));
//            //附近好房
//            String distance = "1.6";
//            List houseInfo = projHouseInfoService.queryProjHouseByhouseIdandLocation(data_house.getNewcode().toString(),
//                    Double.valueOf(data_house.getLon()), Double.valueOf(data_house.getLat()),distance);
//            if (StringTool.isNotEmpty(houseInfo)) {
//                esfMap.put("plot", houseInfo);
//            }
//            //附近小区缺少接口
//            List plotList = plotService.GetNearByhHouseAndDistance(Double.valueOf(data_house.getLon()),
//                    Double.valueOf(data_house.getLat()));
//            if (StringTool.isNotEmpty(plotList)) {
//                esfMap.put("plotList", plotList);
//            }
//            //房源经纪人
//            Map agent = projHouseInfoService.queryAgentByHouseId(Integer.valueOf(houseId));
//            if (agent!=null){
//                esfMap.put("agent",agent);
//            }
//            return NashResult.build(esfMap);
//        }else{
//            return NashResult.Fail("not-found","没找到这个二手房,id:"+houseId);
//        }
//    }


}
