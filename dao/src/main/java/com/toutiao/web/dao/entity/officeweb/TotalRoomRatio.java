package com.toutiao.web.dao.entity.officeweb;

import java.math.BigDecimal;

public class TotalRoomRatio {
    private BigDecimal total;

    private Integer room;

    private BigDecimal ratio;

    /**
     * 用户画像类型1,2,3,4,5,6,7
     */
    private Integer userType;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}