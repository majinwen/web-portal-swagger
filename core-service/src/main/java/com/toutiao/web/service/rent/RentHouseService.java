package com.toutiao.web.service.rent;

import com.toutiao.web.domain.query.RentHouseQuery;

import java.util.List;
import java.util.Map;

/**
 *
 * 租房接口
 *
 */
public interface RentHouseService {
    Map queryNearHouseByDistance(RentHouseQuery rentHouseQuery);

    Map queryHouseById(RentHouseQuery rentHouseQuery);

    Map queryHouseByparentId(RentHouseQuery rentHouseQuery);

    Map queryAgentByHouseId(String houseId);

//    String queryHouseNumByparentId(Integer parentId);

    /**
     * 据筛选条件查询(普租、公寓)
     * @param rentHouseQuery
     * @return
     */
    Map<String,Object> getRentHouseList(RentHouseQuery rentHouseQuery);


}
