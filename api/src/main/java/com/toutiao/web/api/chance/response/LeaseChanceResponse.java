package com.toutiao.web.api.chance.response;

import com.toutiao.web.common.commonmodel.Money;
import lombok.Data;

import java.util.Date;

/**
 * Created by nashwork on 2017/10/10.
 */
@Data
public class LeaseChanceResponse {

    private Integer id;

    private String name;

    private Integer customerId;

    private Integer resourceEnum;

    private Integer resourceId;

    private Date expectSignDate;

    private Integer[] intentCircle;

    private Integer stationCount;

    private Integer budgetBegin;

    private Integer budgetEnd;

    private Integer housePurpose;

    private Integer intentArea;

    private Integer productTypeEnum;

    private String memo;

    private String belongUserId;

    private Integer stage;

    private Integer status;

    private String prevBelongUserId;

    private Date actualSignDate;

    private String loseReason;

    private Integer loseType;

    private Integer resourceEnum2;

    private Date flowInAt;

    private Integer flowInReason;

    private Date flowOutAt;

    private Integer flowOutReason;

    private Integer customerType;

    private Integer resourceType;

    private Date lastFollowAt;

    private String powerFilter;

    private String contractNo;

    private Integer contractStatus;

    private Integer[] productId;

    private String depositNo;

    private Money reportPrice;

    private Integer agentId;

    private String customerNamiId;

    private String sfId;

    private String productTypeEnumName;

    private String stageName;

    private String statusName;
}
