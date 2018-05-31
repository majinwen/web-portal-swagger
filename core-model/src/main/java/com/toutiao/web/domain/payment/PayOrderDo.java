package com.toutiao.web.domain.payment;

import lombok.Data;

import java.util.Date;

@Data
public class PayOrderDo {

    private  String comment;

    private  Integer createId;

    private Date createTime;


    private String orderNo;

    private  String productNo;

    private  Integer status;

    private Integer updateId;

    private  Date updateTime;

    private  Integer userId;

    private  String userName;


}
