package com.toutiao.web.common.authentication;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * zhangjinglei 2017/10/19 下午3:18
 */
@Data
public class RoleDataRule implements Serializable{
    private List<Integer> customer;
    private List<Integer> managehouse;
    private List<Integer> entrance;
}
