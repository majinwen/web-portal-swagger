package com.toutiao.app.dao.plot.impl;

import com.toutiao.app.dao.plot.PlotsThemeEsDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 小区专题数据层
 */
@Service
public class PlotsThemeEsDaoImpl implements PlotsThemeEsDao {
	@Value("${plot.index}")
	private String index;

	@Value("${plot.parent.type}")
	private String parentType;

	@Value("${plot.child.type}")
	private String childType;

	@Autowired
	private ESClientTools esClientTools;


	@Override
	public SearchResponse getPlotsThemeList(BoolQueryBuilder boolQueryBuilder, Integer sort, Integer pageNum, Integer pageSize) {
		TransportClient client = esClientTools.init();
		SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
		//TODO 可能有排序逻辑
		srb.addSort("_uid", SortOrder.DESC);
		SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setFrom((pageNum - 1) * pageSize).setSize(pageSize)
				.execute().actionGet();
		return searchResponse;
	}
}
