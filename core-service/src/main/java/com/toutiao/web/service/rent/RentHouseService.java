package com.toutiao.web.service.rent;

import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.domain.query.RentHouseQuery;

import java.util.List;

/**
 *
 * 租房接口
 *
 */
public interface RentHouseService {
    List queryNearHouseByDistance(RentHouseQuery rentHouseQuery);

    NashResult queryHouseById(RentHouseQuery rentHouseQuery);

    List queryHouseByparentId(RentHouseQuery rentHouseQuery);

    String queryHouseNumByparentId(Integer parentId);

    /**
     * 据筛选条件查询(普租、公寓)
     * @param rentHouseQuery
     * @return
     */
    Map<String,Object> getRentHouseList(RentHouseQuery rentHouseQuery);


}
