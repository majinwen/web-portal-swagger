package com.toutiao.app.service.newhouse;

import com.toutiao.app.domain.newhouse.NewHouseLayoutCountDomain;
import com.toutiao.app.domain.newhouse.NewHouseLayoutDo;

import java.util.List;

public interface NewHouseLayoutService {

    /**
     * 根据新房id获取该id下所有的户型以及数量
     * @param newHouseId
     * @return
     */
    NewHouseLayoutCountDomain getNewHouseLayoutByNewHouseId(Integer newHouseId);

    /**
     * 根据新房id获取该户型下的户型列表
     * @param newHouseId
     * @param roomCount
     * @return
     */
    List<NewHouseLayoutDo> getNewHouseLayoutList(Integer newHouseId, Integer roomCount);

}
