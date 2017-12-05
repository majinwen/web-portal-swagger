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
public class UserRequest {

    @NotNull(groups = {First.class})
    private Integer id;

    @NotNull(groups = {Second.class})
    private String loginName;

    @NotNull(groups = {Second.class})
    private String password;

    @NotNull(groups = {Second.class})
    private String userName;

    private Integer createId;

    private Date createTime;

    private Integer modifyId;

    private Date modifyTime;
}
