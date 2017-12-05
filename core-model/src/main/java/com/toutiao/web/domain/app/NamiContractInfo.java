package com.toutiao.web.domain.app;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 *
 * @Project :core-base-parent
 * @Author : kewei@nash.work
 * @Date : 2017-09-28 下午3:58 星期四
 * @Version : v1.0
 **/
@Data
public class NamiContractInfo implements Serializable {
    private static final long serialVersionUID = 4958973024750817750L;

    /**
     * 城市
     */
    private String cityName;
    /**
     * 区域
     */
    private String districtName;
    /**
     * 商圈
     */
    private String circleName;
    /**
     * 项目
     */
    private String projectName;
    /**
     * 楼座
     */
    private String buildingName;
    /**
     * 房间
     */
    private String roomName;
    /**
     * 合同编号
     */
    private String contractNo ;
}
