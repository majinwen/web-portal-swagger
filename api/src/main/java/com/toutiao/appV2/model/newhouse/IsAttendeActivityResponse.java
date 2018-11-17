package com.toutiao.appV2.model.newhouse;

import com.toutiao.app.domain.activity.UserNewBuildingActivity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author : zym
 * @date : 2018/11/17 17:25
 * @desc :
 */
@Data
@Builder
public class IsAttendeActivityResponse {

    private List<UserNewBuildingActivity> data;

    private Integer totalNum;
}
