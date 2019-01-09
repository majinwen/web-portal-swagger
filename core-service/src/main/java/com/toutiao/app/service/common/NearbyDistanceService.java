package com.toutiao.app.service.common;

import java.util.Map;

public interface NearbyDistanceService {

    String getTrafficWithOneSubwayLine(Map subwayDistinceMap, Integer subwayLineId, Integer[] subwayStationIdArray);

    String getNearbyDistanceByTraffic(String traffic, String frontName);
}
