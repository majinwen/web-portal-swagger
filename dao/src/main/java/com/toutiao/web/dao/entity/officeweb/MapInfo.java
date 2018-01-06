package com.toutiao.web.dao.entity.officeweb;

public class MapInfo {
    /**
     * 序号
     */
    private Integer id;

    /**
     * 楼盘Id
     */
    private Integer newcode;

    /**
     * 地图信息结果
     */
    private Object dataInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNewcode() {
        return newcode;
    }

    public void setNewcode(Integer newcode) {
        this.newcode = newcode;
    }

    public Object getDataInfo() {
        return dataInfo;
    }

    public void setDataInfo(Object dataInfo) {
        this.dataInfo = dataInfo;
    }
}