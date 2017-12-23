package com.toutiao.web.service;

import com.toutiao.web.dao.entity.officeweb.PriceTrend;

import java.util.List;
import java.util.Map;

/**
 * Created by 18710 on 2017/12/14.
 */
public interface PriceTrendService {
   Map<String,List<PriceTrend>> priceTrendList(PriceTrend priceTrend);
}
