package com.toutiao.app.service.common;

import org.elasticsearch.search.SearchHit;

public interface NearbyDistanceService {

    String getDefaultTraffic(SearchHit searchHit, String trafficKey);

    String getTrafficWithOneSubwayLine(SearchHit searchHit, String trafficKey, Integer subwayLineId, Integer[] subwayStationIdArray);

    String getNearbyDistanceByTraffic(String traffic, String frontName);
}
