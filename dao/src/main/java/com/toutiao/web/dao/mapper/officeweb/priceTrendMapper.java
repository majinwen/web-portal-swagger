package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.priceTrend;

public interface priceTrendMapper extends BaseDao {
    int insert(priceTrend record);

    int insertSelective(priceTrend record);
}