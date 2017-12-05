package com.toutiao.web.domain.chance;

import lombok.Data;

/**
 * Created by jyl on 17/9/21.
 */
@Data
public class ChanceAgent {
    private String agentName;//联系人姓名
    private Integer type;//联系人类型
    private Integer relateTableId;//关联表纪录ID
    private Integer gender;
    private Integer idcard_type;
    private String idcard_no;
    private Integer state;
    private String memo;
    private String create_employee_sn;
    private String create_user_name;
    private String assign_employee_sn;
    private String assign_employee_name;
    private String title;
    private String mobile_phone;
    private String address;
    private String email;
    private String wechat_id;
    private Integer is_new;
    private Integer is_deputy;
    private String namiId;

    @Override
    public String toString() {
        return "ChanceAgent{" +
                "agentName='" + agentName + '\'' +
                ", type=" + type +
                ", relateTableId=" + relateTableId +
                ", gender=" + gender +
                ", idcard_type=" + idcard_type +
                ", idcard_no='" + idcard_no + '\'' +
                ", state=" + state +
                ", memo='" + memo + '\'' +
                ", create_employee_sn='" + create_employee_sn + '\'' +
                ", create_user_name='" + create_user_name + '\'' +
                ", assign_employee_sn='" + assign_employee_sn + '\'' +
                ", assign_employee_name='" + assign_employee_name + '\'' +
                ", title='" + title + '\'' +
                ", mobile_phone='" + mobile_phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", wechat_id='" + wechat_id + '\'' +
                ", is_new=" + is_new +
                ", is_deputy=" + is_deputy +
                ", namiId='" + namiId + '\'' +
                '}';
    }
}
