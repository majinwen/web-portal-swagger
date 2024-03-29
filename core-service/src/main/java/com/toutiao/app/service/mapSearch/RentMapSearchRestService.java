package com.toutiao.app.service.mapSearch;

import com.toutiao.app.domain.mapSearch.RentMapSearchDoQuery;
import com.toutiao.app.domain.mapSearch.RentMapSearchDomain;
import com.toutiao.app.domain.mapSearch.RentOfPlotListDo;

import java.util.Map;

public interface RentMapSearchRestService {
    /**
     * 地图找房-租房查询
     * @param rentMapSearchDoQuery
     * @param city
     * @return
     */
    RentMapSearchDomain rentMapSearch(RentMapSearchDoQuery rentMapSearchDoQuery, String city);

    /**
     * 获取区域商圈信息
     * @param id
     * @param type
     * @return
     */
    Map getDistanceAndAreainfo(Integer id,Integer type);

    /**
     * 小区的租房
     * @param rentMapSearchDoQuery
     * @return
     */
    RentOfPlotListDo getRentOfPlot(RentMapSearchDoQuery rentMapSearchDoQuery, String city);

    /**
     * 获取地铁/价格信息
     * @param id
     * @param city_id
     * @return
     */
    Map getSubwayInfo(Integer id,Integer city_id);

    /**
     * 获取地铁线id获取地铁站名称
     * @param id
     * @param city_id
     * @return
     */
    Map getSubwayLineInfo(Integer id,Integer city_id);

    /**
     * 查询区域或商圈下小区的个数
     * @param rentMapSearchDoQuery
     * @param id
     * @param cityId
     * @param type
     * @return
     */
    Integer queryDistrictOrAreaRentCount(RentMapSearchDoQuery rentMapSearchDoQuery, Integer id, String cityId, Integer type);

}
