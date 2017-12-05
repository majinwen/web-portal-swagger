package com.toutiao.web.dao.entity.admin;

import lombok.Data;

import java.util.Date;

@Data
public class SysMenuEntity {

    private Integer id;

    private String code;

    private String name;

    private Integer type;

    private Integer createId;

    private Date createTime;

    private Integer modifyId;

    private Date modifyTime;

    private Integer parentId;

    private Integer status;

    private Integer orderColumn;
}