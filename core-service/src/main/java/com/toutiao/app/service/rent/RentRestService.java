package com.toutiao.app.service.rent;

import com.toutiao.app.domain.rent.*;

import java.util.Date;
import java.util.List;

public interface RentRestService {
    /**
     * 根据id查找出租房源详情
     * @param rentId
     * @return
     */
    RentDetailsDo queryRentDetailByHouseId(String rentId,String cityCode);

    /**
     * 根据小区id查询该小区下的出租房源
     * @param plotId
     * @return
     */
    RentDetailsListDo queryRentListByPlotId(Integer plotId,Integer rentType,Integer pageNum,  String city);

    /**
     * 根据小区id查询该小区下的出租房源个数
     * @param plotId
     * @return
     */
    RentNumListDo queryRentNumByPlotId(Integer plotId, String city);

    /**
     * 根据出租房源的id查询该房源的经纪人
     * @param rentId
     * @return
     */
 //   RentAgentDo queryRentAgentByRentId(String rentId);

    /**
     * 附近5km内出租房源(规则:app的是吧，那就优先三公里的录入房源由近到远)
     * @param nearHouseDo
     * @return
     */
    List<RentDetailsFewDo> queryNearHouseByLocation(NearHouseDo nearHouseDo);

    /**
     * 出租推荐房源（7天内录入）
     * @param rentHouseDoQuery
     * @return
     */
    RentDetailsListDo getRentList(RentHouseDoQuery rentHouseDoQuery, String city);

    /**
     * 推优房源
     * @param rentHouseDoQuery
     * @return
     */
    RentDetailsFewDo queryRecommendRent(RentHouseDoQuery rentHouseDoQuery, String city);

    /**
     * 租房搜索结果
     * @param rentHouseDoQuery
     * @return
     */
    RentDetailsListDo getRentHouseSearchList(RentHouseDoQuery rentHouseDoQuery, String city);

    /**
     * 租房搜索结果
     * @param rentHouseDoQuery
     * @return
     */
    RentDetailsListDo getCommuteRentHouseSearchList(RentHouseDoQuery rentHouseDoQuery, String city);

    /**
     * 租房判断是否上传默认图
     * @param importTime
     * @param today
     * @param image
     * @return
     */
    int isDefaultImage(String importTime, Date today, String image);

}
