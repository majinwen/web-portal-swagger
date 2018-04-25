package com.toutiao.app.service.sellhouse;

import com.toutiao.app.domain.sellhouse.NearBySellHousesDo;
import org.elasticsearch.index.query.BoolQueryBuilder;

import java.util.List;

public interface FilterSellHouseChooseService {


    /**
     * 过滤二手房查询条件
     * @param nearBySellHousesDo
     * @return
     */
    BoolQueryBuilder filterChoose(NearBySellHousesDo nearBySellHousesDo);


    List<String> filterKeyWords(String keywords);

}
