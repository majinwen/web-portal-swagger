package com.toutiao.app.service.rent;

import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.domain.query.RentRequest;

import java.util.Map;

public interface AppRentService {
    NashResult queryRentDetailByHouseId(RentRequest rentRequest);
}
