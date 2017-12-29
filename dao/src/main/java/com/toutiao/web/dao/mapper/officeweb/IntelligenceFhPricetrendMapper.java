package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhPricetrend;

public interface IntelligenceFhPricetrendMapper extends BaseDao {
    int insert(IntelligenceFhPricetrend record);

    int insertSelective(IntelligenceFhPricetrend record);
}