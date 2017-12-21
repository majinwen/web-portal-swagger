package com.toutiao.web.service;

import com.sun.jdi.IntegerType;
import com.toutiao.web.dao.entity.officeweb.PriceTrend;
import com.toutiao.web.dao.mapper.officeweb.PriceTrendMapper;
import com.toutiao.web.service.PriceTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 18710 on 2017/12/14.
 */
@Service
@Transactional
public class PriceTrendServiceImpl implements PriceTrendService {

    @Autowired
    private PriceTrendMapper priceTrendMapper;

   public Map<String,List<PriceTrend>> priceTrendList(PriceTrend priceTrend){
       List<PriceTrend> priceTrendList = priceTrendMapper.searchPriceTrendList(priceTrend);

       //
       List<PriceTrend> ptCD0 =new ArrayList<>();
       List<PriceTrend> ptCD1 =new  ArrayList<>();
       List<PriceTrend> ptCD2 =new  ArrayList<>();
       for (PriceTrend ptitem:priceTrendList) {
           if (0 == ptitem.getContrastDA()){
               ptCD0.add(ptitem);
           }else if (1 == ptitem.getContrastDA()){
               ptCD1.add(ptitem);
           }else if (2 == ptitem.getContrastDA()){
               ptCD2.add(ptitem);
           }
       }

      Map<String ,List<PriceTrend>> priceList=new HashMap<>();
      priceList.put("buildingline",ptCD0);
      priceList.put("arealine",ptCD1);
      priceList.put("tradearealine",ptCD2);

 /*     List<Map<String ,List<PriceTrend>>> list=new ArrayList<>();
        list.add(priceList);*/
      return priceList;
   }
}
