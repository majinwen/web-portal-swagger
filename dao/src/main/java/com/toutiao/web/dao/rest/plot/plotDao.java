package com.toutiao.web.dao.rest.plot;

import java.util.List;
import java.util.Map;

public interface plotDao {
    /**
     * 通过id查询详情
     * @param id
     * @return
     */
    Map queryPlotDetail(Integer plotId);

    /**
     * 根据坐标和距离查询附近的小区
     * @param lat
     * @param lon
     * @return
     */
    List queryNearPlotByLocationAndDistance(double lat, double lon, Integer plotId);
}
