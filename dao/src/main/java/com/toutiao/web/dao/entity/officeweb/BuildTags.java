package com.toutiao.web.dao.entity.officeweb;

import lombok.Data;

/**
 * Created by 18710 on 2018/9/28.
 */
@Data
public class BuildTags {
    Integer id;
    Integer type;
    Integer cityId;
    Integer districtId;
    String parkName;
    Integer amount;
}
