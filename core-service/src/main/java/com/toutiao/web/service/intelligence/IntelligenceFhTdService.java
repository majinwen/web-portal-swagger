package com.toutiao.web.service.intelligence;

import java.util.Map;

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
    Map<String,Object> queryTd(Integer totalPrice);
}
