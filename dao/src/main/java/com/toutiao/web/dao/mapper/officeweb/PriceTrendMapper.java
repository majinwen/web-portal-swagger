package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.PriceTrend;

import java.util.List;

public interface PriceTrendMapper extends BaseDao {
    int insert(PriceTrend record);

    int insertSelective(PriceTrend record);

     List<PriceTrend> searchPriceTrendList(PriceTrend priceTrend);

}