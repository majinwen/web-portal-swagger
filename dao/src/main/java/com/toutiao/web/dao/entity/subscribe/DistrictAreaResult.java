package com.toutiao.web.dao.entity.subscribe;

import lombok.Data;

import java.util.List;

/**
 * Created by 18710 on 2018/11/22.
 */
@Data
public class DistrictAreaResult {
    private  Integer value;

    private String label;

    private List<DistrictAreaResult> children;
}
