package com.toutiao.web.api.chance.request;

import com.toutiao.web.common.assertUtils.*;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by jyl on 17/9/12.
 */
@Data
public class LeaseChanceRequest {
    @NotNull(groups={First.class,Fourth.class,Third.class},message = "缺少机会ID")
    private Integer id;
    @NotEmpty(groups = {First.class,Second.class},message = "缺少机会名称")
    private String name;
    //@NotNull(groups = {Second.class})
    private Integer customerId;
    @NotNull(groups = {Second.class},message = "缺少来源渠道")
    private Integer resourceEnum;//不能修改
    //@NotNull(groups = {First.class})
    private Integer resourceId;//不能修改
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date expectSignDate;
    @NotNull(groups = {First.class,Second.class},message = "缺少意向商圈")
    private Integer[] intentCircle;
    //@NotNull(groups = {First.class,Second.class})
    private Integer stationCount;
    @NotNull(groups = {First.class,Second.class},message = "缺少预算")
    private Integer budgetBegin;
    //@NotNull(groups = {First.class,Second.class})
    //private Integer budgetEnd;
    //@NotNull(groups = {First.class,Second.class})
    //private Integer housePurpose;
    @NotNull(groups = {First.class,Second.class},message = "缺少意向面积")
    private Integer intentArea;
    @NotNull(groups = {Second.class},message = "缺少产品类型枚举")
    private Integer productTypeEnum;//不能修改

    private String memo;

    //private String belongUserId;

    private Integer stage;
    //@NashEnumsCheck(ChanceStatusEnum.class)
    //private Integer status;

    //private String prevBelongUserId;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date actualSignDate;
    @NotNull(groups={Fourth.class},message = "缺少输单原因")
    private String loseReason;
    @NotNull(groups={Fourth.class},message = "缺少输单类别")
    private Integer loseType;

    //private String creator;

    //private Date createAt;

    //private String modifier;

    //private Date modifyAt;
    private Integer resourceEnum2;//不能修改

    //private Date flowInAt;

    //private Integer flowInReason;

    //private Date flowOutAt;

    //private Integer flowOutReason;
    @NotNull(groups={First.class,Second.class},message = "缺少客户标识")
    private Integer customerType;
    @NotNull(groups={First.class,Second.class},message = "缺少渠道标识")
    private Integer resourceType;
    @NotNull(groups = {Third.class},message = "缺少合同号")
    private String contractNo;

    private Integer contractStatus;

    //private Integer houseId;

    //private Money reportPrice;
    //@NotNull(groups = {Third.class})
    private Integer[] productId;
}
