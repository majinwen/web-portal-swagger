package com.toutiao.app.service.sellhouse;

import com.toutiao.app.domain.sellhouse.NearBySellHouseQueryDo;
import com.toutiao.app.domain.sellhouse.RecommendEsf5DoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseDoQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;

import java.util.List;

public interface FilterSellHouseChooseService {


    /**
     * 过滤附近二手房查询条件
     * @param nearBySellHouseQueryDo
     * @return
     */
    BoolQueryBuilder filterChoose(NearBySellHouseQueryDo nearBySellHouseQueryDo);

    /**
     * 关键字分词
     * @param keywords
     * @return
     */
    List<String> filterKeyWords(String keywords);

    /**
     * 过滤二手房查询条件
     * @param sellHouseQueryDo
     * @return
     */
    BoolQueryBuilder filterSellHouseChoose(SellHouseDoQuery sellHouseQueryDo);

    /**
     * 获取推荐房源
     *
     * @param recommendEsf5DoQuery
     * @return
     */
    BoolQueryBuilder getRecommendEsf5(RecommendEsf5DoQuery recommendEsf5DoQuery);
}
