package com.toutiao.app.api.chance.response.newhouse;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;
@Data
public class NewHouseListDomainResponse {

    @JSONField(name="data")
    private  List<NewHouseListResponse> newHouseListResponse;
    private long totalCount;

}
