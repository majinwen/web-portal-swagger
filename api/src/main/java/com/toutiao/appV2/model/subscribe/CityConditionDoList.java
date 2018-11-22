package com.toutiao.appV2.model.subscribe;

import com.toutiao.web.dao.entity.subscribe.CityConditionDo;
import lombok.Data;

import java.util.List;

/**
 * Created by 18710 on 2018/11/22.
 */
@Data
public class CityConditionDoList {
    Integer total;
    List<CityConditionDo> cityConditionDos;
}
