package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhTd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntelligenceFhTdMapper extends BaseDao {
    int insert(IntelligenceFhTd record);

    int insertSelective(IntelligenceFhTd record);

    /**
     * 根据总价获取目标市场供需走势
     * @param totalPrice
     * @return
     */
    List<IntelligenceFhTd> queryTd(@Param("totalPrice")Integer totalPrice);
}