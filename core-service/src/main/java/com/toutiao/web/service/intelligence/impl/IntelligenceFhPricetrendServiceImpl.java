package com.toutiao.web.service.intelligence.impl;

import com.toutiao.web.dao.entity.officeweb.IntelligenceFhPricetrend;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhTd;
import com.toutiao.web.dao.mapper.officeweb.IntelligenceFhPricetrendMapper;
import com.toutiao.web.domain.intelligenceFh.IntelligenceFhPtRatio;
import com.toutiao.web.service.intelligence.IntelligenceFhPricetrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        IntelligenceFhPtRatio intelligenceFhPtRatio = intelligenceFhPricetrendMapper.queryPriceTrendRatio(totalPrice);
        Map<String,Object> result = new HashMap<>();
        Double[] price = new Double[12];
        Double[] totalprice = new Double[12];
        MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN);
        Double[] ratio = new Double[lists.size()-1];

        if(lists.size()> 0){
            ok:
            for(int i=0;i<lists.size();i++){
                if(ratio.length == i){
                    break ok;
                }
                price[i] = lists.get(i).getPrice().doubleValue();
                ratio[i] = (lists.get(i).getPrice().subtract(lists.get(i+1).getPrice()))
                        .divide(lists.get(i+1).getPrice(),mc).multiply(new BigDecimal(100)).doubleValue();
            }
            Double max=ratio[0];
            Double min=ratio[0];
            for(int i=0; i<ratio.length;i++){
                if(ratio[i]>max) max=ratio[i];
            }
            for(int j = 0; j<ratio.length;j++){
                if(ratio[j]<min)min=ratio[j];
            }
            result.put("minTarget",min);
            result.put("maxTarget",max);
        }
        //目标市场
        Double cityAvgRatio11 = intelligenceFhPtRatio.getMaxtotalprice().divide(new BigDecimal(12),mc)
                .divide(intelligenceFhPtRatio.getMaxtotalprice(),mc).multiply(new BigDecimal(100)).doubleValue();


        result.put("target",cityAvgRatio11);
        result.put("ptlists",lists);
        return result;
    }





}
