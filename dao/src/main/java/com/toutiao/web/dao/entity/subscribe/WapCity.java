package com.toutiao.web.dao.entity.subscribe;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 18710 on 2018/11/22.
 */
@Data
@ApiModel(value = "WapCity", description = "wap城市参数")
public class WapCity
{
    private String cityPinyinInitials;
    private List<HashMap<String,Object>> cityJson;
}
