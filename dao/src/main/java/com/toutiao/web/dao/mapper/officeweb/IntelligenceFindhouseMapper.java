package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFindhouse;

public interface IntelligenceFindhouseMapper extends BaseDao {
    int insert(IntelligenceFindhouse record);

    int insertSelective(IntelligenceFindhouse record);
}