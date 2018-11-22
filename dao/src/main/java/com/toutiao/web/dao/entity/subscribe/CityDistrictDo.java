package com.toutiao.web.dao.entity.subscribe;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by 18710 on 2018/11/22.
 */
@Data
public class CityDistrictDo {
    @NotNull
    private Integer cityId;

    @NotEmpty
    private String districtInfo;
}
