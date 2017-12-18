package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.TotalListedRatio;

public interface TotalListedRatioMapper extends BaseDao {
    int insert(TotalListedRatio record);

    int insertSelective(TotalListedRatio record);
}