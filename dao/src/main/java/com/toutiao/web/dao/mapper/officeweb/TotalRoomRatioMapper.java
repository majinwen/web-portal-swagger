package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.TotalRoomRatio;

public interface TotalRoomRatioMapper extends BaseDao {
    int insert(TotalRoomRatio record);

    int insertSelective(TotalRoomRatio record);
}