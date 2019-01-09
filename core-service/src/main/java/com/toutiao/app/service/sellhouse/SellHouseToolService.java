package com.toutiao.app.service.sellhouse;

import com.toutiao.app.domain.sellhouse.HouseColorLable;
import org.elasticsearch.search.SearchHit;

import java.util.List;

public interface SellHouseToolService {

    List<String> getHouseBarrageFirstList(SearchHit searchHit,String city);

    List<String> getHouseBarrageSecondList(SearchHit searchHit);

    String getCommunityLableStr(SearchHit searchHit,String city);

    String getSubwayDistanceInfoBySubway(SearchHit searchHit);

    List<HouseColorLable> getHouseColorLableListForESF(SearchHit searchHit, String wapName,String city,String districtId,String districtName);

    List<HouseColorLable> getHouseColorLableListForESFDetails(SearchHit searchHit, String wapName,String city,String districtId,String districtName);

    String getDefaultTraffic(SearchHit searchHit, String trafficKey);

    String getTrafficWithOneSubwayLine(SearchHit searchHit, String trafficKey,Integer subwayLineId,Integer[] subwayStationIdArray);

    String getNearbyDistanceByTraffic(String traffic,String frontName);
}
