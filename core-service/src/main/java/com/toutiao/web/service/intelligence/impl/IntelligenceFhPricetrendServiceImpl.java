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

        List<IntelligenceFhPricetrend> lowlists = intelligenceFhPricetrendMapper.queryLowPriceTrend(totalPrice);
        BigDecimal rowfalg = null;
        MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN);
        if(lowlists.size() > 0){
            rowfalg =  lowlists.get(0).getPrice();
        }
        //目标市场
        BigDecimal cityAvgRatio = intelligenceFhPtRatio.getAvgtotalprice()
                .divide(intelligenceFhPtRatio.getMaxtotalprice(),mc).multiply(new BigDecimal(100));

        //最高涨幅
        BigDecimal maxratio =  (intelligenceFhPtRatio.getMaxprice().subtract(rowfalg))
                .divide(rowfalg,mc).multiply(new BigDecimal(100));

        if(maxratio.compareTo(cityAvgRatio)==-1){
            intelligenceFhPtRatio.setRowfalg(1);
        }else {
            intelligenceFhPtRatio.setRowfalg(0);
        }
        intelligenceFhPtRatio.setMaxRatio(maxratio.setScale(2, BigDecimal.ROUND_HALF_UP)+"%");

        //最高跌幅
        BigDecimal minratio = (intelligenceFhPtRatio.getMaxprice().subtract(intelligenceFhPtRatio.getMinprice()))
                .divide(intelligenceFhPtRatio.getMaxprice(),mc).multiply(new BigDecimal(100));

        if(minratio.compareTo(cityAvgRatio)==-1){
            intelligenceFhPtRatio.setLowfalg(1);
        }else {
            intelligenceFhPtRatio.setLowfalg(0);
        }
        intelligenceFhPtRatio.setMinRatio(minratio.setScale(2, BigDecimal.ROUND_HALF_UP)+"%");
        Map<String,Object> result = new HashMap<>();
        result.put("ratio",intelligenceFhPtRatio);
        result.put("trend",lists);

        return result;
    }
}
