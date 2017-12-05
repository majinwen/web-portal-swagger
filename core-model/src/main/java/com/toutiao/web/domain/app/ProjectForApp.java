package com.toutiao.web.domain.app;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 *
 * @Project :core-base-parent
 * @Author : kewei@nash.work
 * @Date : 2017-09-29 下午4:43 星期五
 * @Version : v1.0
 **/
@Data
public class ProjectForApp implements Serializable {
    private static final long serialVersionUID = 4564538073872306600L;
    /**
     *
     * @mbggenerated
     */
    private Integer id;
    /**
     *
     * 项目名称
     * @mbggenerated
     */
    private String name;
}
