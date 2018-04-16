package com.toutiao.app.dao.Appnewhouse;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface AppNewHouseEsDao {

    SearchResponse getNewHouseBulid(BoolQueryBuilder boolQueryBuilder);

    SearchResponse  getNewHouseLayoutByNewCode(Integer newcode);

}
