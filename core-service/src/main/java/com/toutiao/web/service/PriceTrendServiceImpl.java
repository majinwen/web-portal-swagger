package com.toutiao.web.service;

import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.dao.entity.officeweb.PriceTrend;
import com.toutiao.web.dao.mapper.officeweb.PriceTrendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 18710 on 2017/12/14.
 */
@Service
@Transactional
public class PriceTrendServiceImpl implements PriceTrendService {

    @Autowired
    private PriceTrendMapper priceTrendMapper;

   public Map<String,Object> priceTrendList(Integer buildingId,Integer districtId,Integer areaId){
       List<PriceTrend> priceTrendList = priceTrendMapper.newhouseTrendList(buildingId,districtId,areaId);
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-M");
       List<PriceTrend> ptCD0 =new ArrayList<>();
       List<PriceTrend> ptCD1 =new  ArrayList<>();
       List<PriceTrend> ptCD2 =new  ArrayList<>();
       for (PriceTrend ptitem:priceTrendList) {
           if (0 == ptitem.getContrastDA()){
              Date ditem = ptitem.getMonth();
             String udateitem = formatter1.format(ditem);
             ptitem.setTumonth(udateitem);
               ptCD0.add(ptitem);
           }else if (1 == ptitem.getContrastDA()){
               Date ditem = ptitem.getMonth();
               String udateitem = formatter1.format(ditem);
               ptitem.setTumonth(udateitem);
               ptCD1.add(ptitem);
           }else if (2 == ptitem.getContrastDA()){
               Date ditem = ptitem.getMonth();
               String udateitem = formatter1.format(ditem);
               ptitem.setTumonth(udateitem);
               ptCD2.add(ptitem);
           }
       }

 /*      PriceTrend p0Item =  ptCD0.get(0);
       Integer p0month =Integer.parseInt(formatter1.format(p0Item.getMonth()));
       if (p0month!=1){
           for
       }*/


      /* List<String> timeList = new LinkedList<>();
       if (ptCD0.size()>ptCD1.size() && ptCD0.size()>ptCD2.size()) {
           for (PriceTrend pitem : ptCD0) {
               String dateString = formatter.format(pitem.getMonth());
               String dateStringFin = dateString.substring(5,7);
               timeList.add(dateStringFin);
           }
       }else if (ptCD1.size()>ptCD0.size() && ptCD1.size()>ptCD2.size()){
           for (PriceTrend pitem : ptCD1) {
               String dateString = formatter.format(pitem.getMonth());
               String dateStringFin = dateString.substring(0,7);
               timeList.add(dateStringFin);
           }
       }else if (ptCD2.size()>ptCD1.size() && ptCD2.size()>ptCD0.size()){
           for (PriceTrend pitem : ptCD2) {
               String dateString = formatter.format(pitem.getMonth());
               String dateStringFin = dateString.substring(0,7);
               timeList.add(dateStringFin);
           }
       }*/

      List<String> timeList = DateUtil.oneYearList();

      Map<String ,Object> priceList=new HashMap<>();
      priceList.put("buildingline",ptCD0);
      priceList.put("arealine",ptCD1);
      priceList.put("tradearealine",ptCD2);
      priceList.put("mouthList",timeList);

 /*     List<Map<String ,List<PriceTrend>>> list=new ArrayList<>();
        list.add(priceList);*/
      return priceList;
   }
}
