package com.toutiao.web.service.rent;

import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.domain.query.RentHouseQuery;

import java.util.List;
import java.util.Map;

/**
 *
 * 租房接口
 *
 */
public interface RentHouseService {
    NashResult queryNearHouseByDistance(RentHouseQuery rentHouseQuery);

    NashResult queryHouseById(RentHouseQuery rentHouseQuery);

}
