package com.toutiao.web.service.rent;

import com.toutiao.web.domain.query.RentHouseQuery;

import java.util.Map;

/**
 *
 * 租房接口
 *
 */
public interface RentHouseService {


    /**
     * 据筛选条件查询(普租、公寓)
     * @param rentHouseQuery
     * @return
     */
    Map<String,Object> getRentHouseList(RentHouseQuery rentHouseQuery);


}
