package com.toutiao.web.service;

import com.sun.jdi.IntegerType;
import com.toutiao.web.dao.entity.officeweb.PriceTrend;
import com.toutiao.web.dao.mapper.officeweb.PriceTrendMapper;
import com.toutiao.web.service.PriceTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 18710 on 2017/12/14.
 */
@Service
@Transactional
public class PriceTrendServiceImpl implements PriceTrendService {

    @Autowired
    private PriceTrendMapper priceTrendMapper;

   public List<PriceTrend> priceTrendList(PriceTrend priceTrend){
       List<PriceTrend> priceTrendList = priceTrendMapper.searchPriceTrendList(priceTrend);

        return priceTrendList;
   }
}
