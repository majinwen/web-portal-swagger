package com.toutiao.app.dao.plot;

import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public interface AppPlotDao {
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
