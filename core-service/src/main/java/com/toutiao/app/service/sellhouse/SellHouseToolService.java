package com.toutiao.app.service.sellhouse;

import org.elasticsearch.search.SearchHit;

import java.util.List;

public interface SellHouseToolService {

    List<String> getHouseBarrageFirstList(SearchHit searchHit,String city);

    String getCommunityLableStr(SearchHit searchHit,String city);
}
