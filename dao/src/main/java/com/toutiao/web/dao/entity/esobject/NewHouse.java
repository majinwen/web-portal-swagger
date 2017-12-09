package com.toutiao.web.dao.entity.esobject;


import com.toutiao.web.dao.entity.admin.SysUserEntity;
import lombok.Data;

import java.util.List;

@Data
public class NewHouse {

    private String districtId;

    private String districtName;
    private String areaId;
    private String areaName;
    private Integer subwayLine;
    private String subwayStation;
    private String totalPrice;
    private String layoutId;
    private String layoutType;
    private String areaSize;
    private Integer[] tagsId;
    private String[] tagsName;
    private String buildFormId;
    private String buildingAge;
    private String purposeId;
    private String purposeName;
    private Integer isLift;
    private String ownershipId;
    private String projName;
    private String nickName;
    private String averagePrice;
    private String[] imgs;
    private String saledate;
    private String livindate;
    private String developer;
    private String categoryName;
    private String rightYear;
    private String buildCategory;
    private List<SysUserEntity> layoutList;



}
