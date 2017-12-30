package com.toutiao.web.service.intelligence.impl;


import com.toutiao.web.dao.entity.officeweb.IntelligenceFhTd;
import com.toutiao.web.dao.mapper.officeweb.IntelligenceFhTdMapper;
import com.toutiao.web.service.intelligence.IntelligenceFhTdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntelligenceFhTdServiceImpl implements IntelligenceFhTdService {


    @Autowired
    private IntelligenceFhTdMapper intelligenceFhTdMapper;


    /**
     * 根据总价获取目标市场供需走势
     * @param totalPrice
     * @return
     */
    @Override
    public List<IntelligenceFhTd> queryTd(Integer totalPrice) {

        List<IntelligenceFhTd> lists = intelligenceFhTdMapper.queryTd(totalPrice);
        return lists;
    }
}
