package com.toutiao.web.service.rent;

import java.util.List;

/**
 *
 * 租房接口
 *
 */
public interface RentHouseService {
    List GetNearHouseByDistance(Double distance,double lat, double lon);

}
