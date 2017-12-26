package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhTd;

public interface IntelligenceFhTdMapper extends BaseDao {
    int insert(IntelligenceFhTd record);

    int insertSelective(IntelligenceFhTd record);
}