package com.toutiao.app.service.sellhouse;

import com.toutiao.app.domain.sellhouse.HouseColorLable;
import org.elasticsearch.search.SearchHit;

import java.util.List;

public interface SellHouseToolService {

    List<String> getHouseBarrageFirstList(SearchHit searchHit,String city);

    List<String> getHouseBarrageSecondList(SearchHit searchHit);

    String getCommunityLableStr(SearchHit searchHit,String city);

    List<HouseColorLable> getHouseColorLableListForESF(SearchHit searchHit, String wapName,String city,String districtId,String districtName);

    List<HouseColorLable> getHouseColorLableListForESFDetails(SearchHit searchHit, String wapName,String city,String districtId,String districtName);

}
