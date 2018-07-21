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
        Integer areaId = houseBusinessAndRoomDoQuery.getAreaId();
        if (areaId != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("houseBusinessNameId", areaId));
        }

        //户型(室)
        Integer[] layoutId = houseBusinessAndRoomDoQuery.getLayoutId();
        if (StringTool.isNotEmpty(layoutId)) {
            boolQueryBuilder.must(QueryBuilders.termQuery("room", layoutId[0]));
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
        Integer[] layoutId = houseBusinessAndRoomDoQuery.getLayoutId();
        if (StringTool.isNotEmpty(layoutId)) {
            boolQueryBuilder.must(QueryBuilders.termQuery("room", layoutId[0]));
        }
        boolQueryBuilder.must(QueryBuilders.termQuery("hall", 1));
        return boolQueryBuilder;
    }
}
