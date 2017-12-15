package com.toutiao.web.service;

import com.sun.jdi.IntegerType;
import com.toutiao.web.dao.entity.officeweb.PriceTrend;

import java.util.List;

/**
 * Created by 18710 on 2017/12/14.
 */
public interface PriceTrendService {
   List<PriceTrend> priceTrendList(PriceTrend priceTrend);
}
