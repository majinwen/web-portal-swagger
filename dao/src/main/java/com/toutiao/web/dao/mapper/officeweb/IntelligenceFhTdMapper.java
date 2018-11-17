package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhTd;
import com.toutiao.web.domain.intelligenceFh.IntelligenceFhTdRatio;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntelligenceFhTdMapper extends BaseDao {
    int insert(IntelligenceFhTd record);

    int insertSelective(IntelligenceFhTd record);

    /**
     * 根据总价获取目标市场供需走势
     * @param totalPrice
     * @return
     */
    List<IntelligenceFhTd> queryTd(@Param("totalPrice")Integer totalPrice);

    /**
     * 根据总价获取目标市场供需走势占比
     * @param totalPrice
     * @return
     */
    IntelligenceFhTdRatio queryTdRatio(@Param("totalPrice")Integer totalPrice);
}