package com.toutiao.app.dao.Appnewhouse;

import com.toutiao.app.domain.newhouse.NewHouseDetailDo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface AppNewHouseEsDao {

    SearchResponse getNewHouseBulidByNewCode(Integer newcode);

    SearchResponse  getNewHouseLayoutByNewCode(Integer newcode);

}
