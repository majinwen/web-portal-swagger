package com.toutiao.web.api.chance;

import com.toutiao.web.api.chance.request.LeaseChanceRequest;
import com.toutiao.web.common.exceptions.NashRequestException;
import com.toutiao.web.common.restmodel.NashResult;

/**
 * Created by jyl on 17/9/12.
 */
public interface LeaseChanceService {
    NashResult findLeaseChanceListByRange(Integer page,
                                   Integer size,
                                   Integer cityId,Integer districtId,Integer projectId,
                                   Integer circleId,
                                   Integer productTypeId,
                                   Integer stage,
                                   String name,String phone,Integer status);


}
