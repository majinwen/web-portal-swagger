package com.toutiao.app.api.chance.request.advertisement;

import io.swagger.annotations.ApiParam;
import com.toutiao.web.common.assertUtils.First;
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

    @NotEmpty(message = "楼盘id不能为空", groups = {First.class})
    @ApiParam(value = "楼盘id")
    private Integer [] newHouseIds;

}
