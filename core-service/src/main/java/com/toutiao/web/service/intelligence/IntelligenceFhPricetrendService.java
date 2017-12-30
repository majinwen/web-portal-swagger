package com.toutiao.web.service.intelligence;


import com.toutiao.web.dao.entity.officeweb.IntelligenceFhPricetrend;

import java.util.List;

/**
 * 智能找房-价格区间走势
 *
 */
public interface IntelligenceFhPricetrendService {


    /**
     * 根据总价获取区间范围价格走势
     * @param totalPrice
     * @return
     */
    List<IntelligenceFhPricetrend> queryPriceTrend(Integer totalPrice);


}
