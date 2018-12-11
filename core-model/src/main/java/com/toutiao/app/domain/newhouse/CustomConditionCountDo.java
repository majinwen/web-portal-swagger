package com.toutiao.app.domain.newhouse;

import lombok.Data;

/**
 * @ClassName CustomConditionCountDo
 * @Author jiangweilong
 * @Date 2018/12/11 2:57 PM
 * @Description:
 **/

@Data
public class CustomConditionCountDo {

    /**
     * 总数
     */
    private Integer totalCount;

    /**
     * 定制总数
     */
    private Integer customCount;

    /**
     * 描述
     */
    private String desc;
}
