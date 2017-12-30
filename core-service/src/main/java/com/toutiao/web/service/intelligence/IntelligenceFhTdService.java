package com.toutiao.web.service.intelligence;


import com.toutiao.web.dao.entity.officeweb.IntelligenceFhTd;

import java.util.List;

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
    List<IntelligenceFhTd> queryTd(Integer totalPrice);
}
