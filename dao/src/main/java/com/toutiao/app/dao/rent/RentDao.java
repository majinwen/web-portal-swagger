package com.toutiao.app.dao.rent;

import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public interface RentDao {
    /**
     * 查询小区内的出租房屋(普租+公寓)
     * @param plotId
     * @return
     */
   Map queryHouseByPlotId(Integer plotId);
}
