package com.toutiao.app.service.plot;

import com.toutiao.app.domain.plot.PlotsEsfRoomCountDomain;
import com.toutiao.app.domain.sellhouse.SellAndClaimHouseDetailsDo;
import com.toutiao.app.domain.sellhouse.SellHouseDo;

import java.util.List;


/**
 * 小区详情下二手房列表
 */
public interface PlotsEsfRestService {

    /**
     * 根据小区id获取小区房源数量
     * @param plotsId
     * @return
     */
    PlotsEsfRoomCountDomain queryPlotsEsfByPlotsId(Integer plotsId);

    /**
     * 根据小区id，户型查询房源列表
     * @param plotsId
     * @param room
     * @return
     */
    List<SellAndClaimHouseDetailsDo> getEsfByPlotsIdAndRoom(Integer plotsId, Integer room, Integer pageNum, Integer pageSize);


    /**
     * 根据小区id获取小区房源数量
     * @param plotsId
     * @return
     */
    PlotsEsfRoomCountDomain queryHouseCountByPlotsId(Integer plotsId);
}
