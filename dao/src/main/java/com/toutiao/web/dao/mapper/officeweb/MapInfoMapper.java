package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.MapInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface MapInfoMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MapInfo record);

    int insertSelective(MapInfo record);

    MapInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MapInfo record);

    int updateByPrimaryKey(MapInfo record);

    MapInfo selectByNewCode(Integer newcode);
}