package com.toutiao.app.api.chance.response.sellhouse;

import com.toutiao.app.domain.sellhouse.SellHouseBeSureToSnatchDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.List;

@Data
public class SellHouseBeSureToSnatchResponse {

    @ChangeName("data")
    List<SellHouseBeSureToSnatchDo> sellHouseBeSureToSnatchDos ;

    private  Integer subscribeId;


}
