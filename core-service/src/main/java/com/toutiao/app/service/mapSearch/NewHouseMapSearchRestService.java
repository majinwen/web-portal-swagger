package com.toutiao.app.service.mapSearch;

import com.toutiao.app.domain.mapSearch.NewHouseMapSearchBuildDomain;
import com.toutiao.app.domain.mapSearch.NewHouseMapSearchDistrictDomain;
import com.toutiao.app.domain.mapSearch.NewHouseMapSearchDoQuery;

/**
 * @ClassName NewHouseMapSearchRestService
 * @Author jiangweilong
 * @Date 2018/11/24 7:16 PM
 * @Description:
 **/
public interface NewHouseMapSearchRestService {

    NewHouseMapSearchDistrictDomain newHouseMapSearchByDistrict(NewHouseMapSearchDoQuery newHouseMapSearchDoQuery, String city);


    NewHouseMapSearchDistrictDomain newHouseMapSubwaySearch(NewHouseMapSearchDoQuery newHouseMapSearchDoQuery, String city);

    NewHouseMapSearchBuildDomain newHouseMapSearchByBuild(NewHouseMapSearchDoQuery newHouseMapSearchDoQuery, String city);

    Integer queryDistiictNewHouseCount(Integer districtId, String city);
}
