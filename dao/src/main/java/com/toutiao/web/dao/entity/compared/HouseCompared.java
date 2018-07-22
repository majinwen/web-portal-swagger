package com.toutiao.web.dao.entity.compared;

import java.util.Date;

public class HouseCompared {
    private Integer id;

    private Integer userId;

    private String houseId;

    private Short houseStatus;

    private Date createTime;

    private Short isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId == null ? null : houseId.trim();
    }

    public Short getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(Short houseStatus) {
        this.houseStatus = houseStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getIsDel() {
        return isDel;
    }

    public void setIsDel(Short isDel) {
        this.isDel = isDel;
    }
}