package com.toutiao.app.service.rent;

import com.toutiao.app.domain.rent.RentAgentDo;
import com.toutiao.app.domain.rent.RentDetailsDo;
import com.toutiao.app.domain.rent.RentDetailsFewDo;

import java.util.List;

public interface RentRestService {
    /**
     * 根据id查找出租房源详情
     * @param rentId
     * @return
     */
    RentDetailsDo queryRentDetailByHouseId(String rentId);

    /**
     * 根据小区id查询该小区下的出租房源
     * @param plotId
     * @return
     */
    List<RentDetailsFewDo> queryRentListByPlotId(Integer plotId,Integer rentType,Integer pageNum);

    /**
     * 根据出租房源的id查询该房源的经纪人
     * @param rentId
     * @return
     */
    RentAgentDo queryRentAgentByRentId(String rentId);
}
