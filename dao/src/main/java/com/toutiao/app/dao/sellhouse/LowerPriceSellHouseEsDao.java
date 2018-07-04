package com.toutiao.app.dao.sellhouse;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

/**
 * 捡漏房数据层
 *
 * @author zym
 */
public interface LowerPriceSellHouseEsDao {
	/**
	 * 获取捡漏房
	 *
	 * @param query query
	 * @param sort  排序
	 * @return SearchResponse
	 */
	SearchResponse getLowerPriceSellHouse(BoolQueryBuilder query, Integer sort);
}
