package com.toutiao.web.service.intelligence.impl;

import com.toutiao.web.dao.entity.officeweb.IntelligenceFhPricetrend;
import com.toutiao.web.dao.mapper.officeweb.IntelligenceFhPricetrendMapper;
import com.toutiao.web.service.intelligence.IntelligenceFhPricetrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntelligenceFhPricetrendServiceImpl implements IntelligenceFhPricetrendService{

    @Autowired
    private IntelligenceFhPricetrendMapper intelligenceFhPricetrendMapper;


    /**
     * 根据总价获取区间范围价格走势
     * @param totalPrice
     * @return
     */
    @Override
    public List<IntelligenceFhPricetrend> queryPriceTrend(Integer totalPrice) {

        List<IntelligenceFhPricetrend> lists = intelligenceFhPricetrendMapper.queryPriceTrend(totalPrice);
        return lists;
    }
}
