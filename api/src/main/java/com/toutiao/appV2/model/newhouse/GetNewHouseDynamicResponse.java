package com.toutiao.appV2.model.newhouse;

import com.toutiao.app.domain.newhouse.NewHouseDynamicDo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetNewHouseDynamicResponse {

    private List<NewHouseDynamicDo> data;

    private Integer totalNum;
}
