package com.toutiao.app.dao.plot;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;


public interface PlotsThemeEsDao {
	SearchResponse getPlotsThemeList(BoolQueryBuilder boolQueryBuilder, Integer sort, Integer pageNum, Integer pageSize);
}
