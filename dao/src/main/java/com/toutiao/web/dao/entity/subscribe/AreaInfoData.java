package com.toutiao.web.dao.entity.subscribe;

import lombok.Data;

import java.util.List;

/**
 * Created by 18710 on 2018/11/22.
 */
@Data
public class AreaInfoData {
    private Integer districtId;

    private String name;

    private List<CircleData> children;
}
