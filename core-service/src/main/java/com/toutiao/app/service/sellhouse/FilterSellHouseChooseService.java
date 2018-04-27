package com.toutiao.app.service.sellhouse;

import com.toutiao.app.domain.sellhouse.ChooseSellHouseDo;
import com.toutiao.app.domain.sellhouse.NearBySellHousesDo;
import org.elasticsearch.index.query.BoolQueryBuilder;

import java.util.List;

public interface FilterSellHouseChooseService {


    /**
     * 过滤附近二手房查询条件
     * @param nearBySellHousesDo
     * @return
     */
    BoolQueryBuilder filterChoose(NearBySellHousesDo nearBySellHousesDo);

    /**
     * 关键字分词
     * @param keywords
     * @return
     */
    List<String> filterKeyWords(String keywords);

    /**
     * 过滤二手房查询条件
     * @param chooseSellHouseDo
     * @return
     */
    BoolQueryBuilder filterSellHouseChoose(ChooseSellHouseDo chooseSellHouseDo);

}
