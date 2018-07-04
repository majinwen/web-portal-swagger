package com.toutiao.app.api.chance.request.sellhouse;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import lombok.Data;

@Data
public class SellHouseBeSureToSnatchRequest extends BaseQueryRequest {

    private  String sortFile="updateTimeSort";

    private  Integer  sort=1;

    private  Integer isNew;
}
