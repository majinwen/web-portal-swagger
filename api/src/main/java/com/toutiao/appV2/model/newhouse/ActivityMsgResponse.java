package com.toutiao.appV2.model.newhouse;

import com.toutiao.app.domain.activity.UserNewBuildingActivityDo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author zym
 */
@Data
@Builder
public class ActivityMsgResponse {
    private List<UserNewBuildingActivityDo> data;

    private Integer totalNum;
}
