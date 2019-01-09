package com.toutiao.app.service.common;

import org.elasticsearch.search.SearchHit;

public interface NearbyDistanceService {

    String getTrafficWithOneSubwayLine(SearchHit searchHit, Integer subwayLineId, Integer[] subwayStationIdArray);

    String getNearbyDistanceByTraffic(String traffic, String frontName);
}
