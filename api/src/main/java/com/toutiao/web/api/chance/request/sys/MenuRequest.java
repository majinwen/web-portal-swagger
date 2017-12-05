package com.toutiao.web.api.chance.request.sys;

import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by 18710 on 2017/11/21.
 */
@Data
public class MenuRequest {
    @NotNull(groups = {First.class})
    private String code;

    @NotNull(groups = {Second.class})
    private String name;

    private Integer type;

    private Integer createId;

    private Date createTime;

    private Integer modifyId;

    private Date modifyTime;

}
