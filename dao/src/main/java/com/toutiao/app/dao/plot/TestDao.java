package com.toutiao.app.dao.plot;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;

public interface TestDao {

    SearchResponse search(SearchRequest searchRequest) throws Exception;
}
