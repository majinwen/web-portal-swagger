package com.toutiao.app.dao.rent;

import com.toutiao.web.domain.query.PlotRequest;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public interface AppRentDao {

    /**
     * 查询小区内的出租房屋(普租+公寓)
     * @param plotId
     * @return
     */
   Map queryHouseByPlotId(Integer plotId);

    /**
     * 根据房源id查询房源的经纪人
     * @param houseId
     * @return
     */
   Map queryAgentByRentId(Integer rentId);

    /**
     * 根据出租房源的id查询出租房源详情
     * @param rentId
     * @return
     */
   Map queryRentByRentId(Integer rentId);
}
