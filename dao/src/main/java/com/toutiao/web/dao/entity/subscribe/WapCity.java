package com.toutiao.web.dao.entity.subscribe;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 18710 on 2018/11/22.
 */
@Data
public class WapCity
{
    private String cityPinyinInitials;
    private List<HashMap<String,Object>> cityJson;
}
