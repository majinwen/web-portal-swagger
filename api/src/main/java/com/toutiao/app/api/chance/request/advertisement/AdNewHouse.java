package com.toutiao.app.api.chance.request.advertisement;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-11-02
 * Time:   15:16
 * Theme: 新房广告入参实体
 */

@Data
public class AdNewHouse {

//    @NotEmpty(message = "楼盘id不能为空")
    private Integer [] newHouseIds;

}
