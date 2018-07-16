package com.toutiao.app.service.sellhouse.impl;

import com.toutiao.app.domain.sellhouse.HouseBusinessAndRoomDoQuery;
import com.toutiao.app.service.sellhouse.FilterBusinessRoomChooseService;
import com.toutiao.web.common.util.StringTool;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;

/**
 * 商圈户型查询条件构造器
 */
@Service
public class FilterBusinessRoomChooseServiceImpl implements FilterBusinessRoomChooseService{
    @Override
    public BoolQueryBuilder filterBusinessRoomChoose(HouseBusinessAndRoomDoQuery houseBusinessAndRoomDoQuery) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //商圈
        String area = houseBusinessAndRoomDoQuery.getAreaName();
        if (StringTool.isNotEmpty(area)) {
            boolQueryBuilder.must(QueryBuilders.termQuery("houseBusinessName", area));
        }

        //户型(室)
        if (StringTool.isNotEmpty(houseBusinessAndRoomDoQuery.getLayoutId())) {
            Integer[] longs = houseBusinessAndRoomDoQuery.getLayoutId();
            boolQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("room", longs)));
        }
        return boolQueryBuilder;
    }

    @Override
    public BoolQueryBuilder filterBusinessRoomAveragePriceChoose(HouseBusinessAndRoomDoQuery houseBusinessAndRoomDoQuery) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //商圈Id
        Integer houseBusinessId = houseBusinessAndRoomDoQuery.getAreaId();
        if (houseBusinessId != null){
            boolQueryBuilder.must(QueryBuilders.termQuery("area_id", houseBusinessId));
        }
        //户型(室)
        if (StringTool.isNotEmpty(houseBusinessAndRoomDoQuery.getLayoutId())) {
            Integer[] longs = houseBusinessAndRoomDoQuery.getLayoutId();
            boolQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("room", longs)));
        }
        boolQueryBuilder.must(QueryBuilders.termQuery("hall", 1));
        return boolQueryBuilder;
    }
}
