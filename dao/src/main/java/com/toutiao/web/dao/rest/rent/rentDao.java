package com.toutiao.web.dao.rest.rent;

import java.util.Map;

public interface rentDao {
    /**
     * 查询小区内的出租房屋(普租+公寓)
     * @param plotId
     * @return
     */
   Map queryHouseByPlotId(Integer plotId);
}
