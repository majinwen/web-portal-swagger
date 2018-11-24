package com.toutiao.web.dao.entity.subscribe;

import com.toutiao.web.dao.entity.subscribe.CityDistrictDo;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by 18710 on 2018/11/22.
 */
@Data
public class CityDistrictiRequest {
    @NotNull
    private List<CityDistrictDo> cityDistrictDos;
}
