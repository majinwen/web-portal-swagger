package com.toutiao.web.service.rent;

import com.toutiao.web.domain.query.RentHouseQuery;

import java.util.List;

/**
 *
 * 租房接口
 *
 */
public interface RentHouseService {
    List GetNearHouseByDistance(RentHouseQuery rentHouseQuery);

}
