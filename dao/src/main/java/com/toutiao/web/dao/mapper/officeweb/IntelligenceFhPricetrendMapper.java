package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhPricetrend;
import com.toutiao.web.domain.intelligenceFh.IntelligenceFhPtRatio;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntelligenceFhPricetrendMapper extends BaseDao {
    int insert(IntelligenceFhPricetrend record);

    int insertSelective(IntelligenceFhPricetrend record);

    /**
     * 根据总价获取区间范围价格走势
     * @param totalPrice
     * @return
     */
    List<IntelligenceFhPricetrend> queryPriceTrend(@Param("totalPrice")Integer totalPrice);
    /**
     * 根据总价获取区间范围价格走势比率
     * @param totalPrice
     * @return
     */
    IntelligenceFhPtRatio queryPriceTrendRatio(@Param("totalPrice")Integer totalPrice);

    List<IntelligenceFhPricetrend> queryLowPriceTrend (@Param("totalPrice")Integer totalPrice);
}