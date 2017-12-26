package com.toutiao.web.dao.entity.officeweb;

public class IntelligenceFhRes {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 智能找房结果
     */
    private Object fhResult;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Object getFhResult() {
        return fhResult;
    }

    public void setFhResult(Object fhResult) {
        this.fhResult = fhResult;
    }
}