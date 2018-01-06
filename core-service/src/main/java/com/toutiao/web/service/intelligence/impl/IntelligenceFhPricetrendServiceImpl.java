package com.toutiao.web.service.intelligence.impl;

import com.toutiao.web.dao.entity.officeweb.IntelligenceFhPricetrend;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhTd;
import com.toutiao.web.dao.mapper.officeweb.IntelligenceFhPricetrendMapper;
import com.toutiao.web.domain.intelligenceFh.IntelligenceFhPtDo;
import com.toutiao.web.domain.intelligenceFh.IntelligenceFhPtRatio;
import com.toutiao.web.service.intelligence.IntelligenceFhPricetrendService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    public Map<String,Object> queryPriceTrend(Integer totalPrice) {

        List<IntelligenceFhPricetrend> lists = intelligenceFhPricetrendMapper.queryPriceTrend(totalPrice);
//        IntelligenceFhPtRatio intelligenceFhPtRatio = intelligenceFhPricetrendMapper.queryPriceTrendRatio(totalPrice);
        Map<String,Object> result = new HashMap<>();
        Double[] price = new Double[12];
        MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN);
        Double[] ratio = new Double[lists.size()-1];
        Double[] tarratio = new Double[lists.size()-1];
        List<IntelligenceFhPtDo> intelligenceFhPtDos = new ArrayList<>();
        Double cityAvgRatio = 0.0;
        if(lists.size()> 0){
            ok:
            for(int i=0;i<lists.size();i++){
                if(ratio.length == i){
                    break ok;
                }
//                price[i] = lists.get(i).getPrice().doubleValue();
                ratio[i] = (lists.get(i).getPrice().subtract(lists.get(i+1).getPrice()))
                        .divide(lists.get(i+1).getPrice(),mc).multiply(new BigDecimal(100)).doubleValue();
                tarratio[i]= (lists.get(i).getTotalPrice().subtract(lists.get(i+1).getTotalPrice()))
                        .divide(lists.get(i+1).getTotalPrice(),mc).doubleValue();
            }
            Double max=ratio[0];
            Double min=ratio[0];
            for(int i=0; i<ratio.length;i++){
                if(ratio[i]>max) max=ratio[i];
            }
            for(int j = 0; j<ratio.length;j++){
                if(ratio[j]<min)min=ratio[j];
            }

            for(int i = 0; i<tarratio.length;i++){
                cityAvgRatio = cityAvgRatio+tarratio[i];
            }

            result.put("minTarget",min);
            result.put("maxTarget",max);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            for(IntelligenceFhPricetrend intelligenceFhPricetrend : lists){
                IntelligenceFhPtDo intelligenceFhPtDo = new IntelligenceFhPtDo();
                try {
                    BeanUtils.copyProperties(intelligenceFhPtDo,intelligenceFhPricetrend);
                    intelligenceFhPtDo.setPeriodicTime(sdf.format(intelligenceFhPricetrend.getPeriodicTime()));
                    intelligenceFhPtDos.add(intelligenceFhPtDo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //目标市场
//        Double cityAvgRatio11 = intelligenceFhPtRatio.getAvgtotalprice()
//                .divide(intelligenceFhPtRatio.getSumtotalprice(),mc).multiply(new BigDecimal(100)).doubleValue();
        cityAvgRatio = cityAvgRatio/12*100;
        result.put("target",cityAvgRatio);
        result.put("ptlists",intelligenceFhPtDos);
        result.put("searchPrice",totalPrice);
        return result;
    }
}
