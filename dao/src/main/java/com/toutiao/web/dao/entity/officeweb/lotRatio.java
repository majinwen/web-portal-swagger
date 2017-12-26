package com.toutiao.web.dao.entity.officeweb;

public class lotRatio {
    /**
     * 主键
     */
    private Integer buildingId;

    /**
     * 小区名称
     */
    private String buildingName;

    /**
     * 换手率
     */
    private String turnoverRate;

    /**
     * 环比
     */
    private String huanbi;

    /**
     * 同比
     */
    private String tongbi;

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? null : buildingName.trim();
    }

    public String getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(String turnoverRate) {
        this.turnoverRate = turnoverRate == null ? null : turnoverRate.trim();
    }

    public String getHuanbi() {
        return huanbi;
    }

    public void setHuanbi(String huanbi) {
        this.huanbi = huanbi == null ? null : huanbi.trim();
    }

    public String getTongbi() {
        return tongbi;
    }

    public void setTongbi(String tongbi) {
        this.tongbi = tongbi == null ? null : tongbi.trim();
    }
}