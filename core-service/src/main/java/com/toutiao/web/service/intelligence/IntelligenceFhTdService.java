package com.toutiao.web.service.intelligence;

import com.toutiao.app.domain.Intelligence.PriceRatioDo;

/**
 *  智能找房目标市场供需走势
 *
 *
 */
public interface IntelligenceFhTdService {



    /**
     * 根据总价获取目标市场供需走势
     * @param totalPrice
     * @return
     */
    PriceRatioDo queryTd(Integer totalPrice);
}
