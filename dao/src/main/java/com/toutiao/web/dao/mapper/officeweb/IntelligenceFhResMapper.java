package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
import org.springframework.stereotype.Repository;

@Repository
public interface IntelligenceFhResMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(IntelligenceFhRes record);

    int insertSelective(IntelligenceFhRes record);

    IntelligenceFhRes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IntelligenceFhRes record);

    int updateByPrimaryKey(IntelligenceFhRes record);

    IntelligenceFhRes selectByUserPhone(String userPhone);
}