package com.toutiao.appV2.model.Intelligence;

import com.toutiao.app.domain.newhouse.CustomConditionDetailsDo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CustomConditionDetailsResponse
 * @Author jiangweilong
 * @Date 2018/12/12 4:03 PM
 * @Description: 符合定制条件的房源分布
 **/

@Data
public class CustomConditionDetailsResponse {

   private List<CustomConditionDetailsDo> data;

}
