package com.toutiao.web.common.authentication;

import lombok.Data;

import java.util.Date;

@Data
public class menu {
    private Integer id;

    private String code;

    private String name;

    private Integer type;

    private Integer parentId;

    private Integer status;

    private Integer orderColumn;
}
