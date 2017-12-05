package com.toutiao.web.domain.app;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 *
 * @Project :core-base-parent
 * @Author : kewei@nash.work
 * @Date : 2017-09-29 下午4:44 星期五
 * @Version : v1.0
 **/
@Data
public class ProductForApp implements Serializable {
    private static final long serialVersionUID = 1879274726725078790L;

    /**
     * 产品ID
     */
    private Integer id;

    /**
     * 产品名称
     */
    private String productname;
}
