package com.toutiao.web.dao.entity.officeweb;

import java.math.BigDecimal;
import java.util.Date;

public class IntelligenceFindhouse {
    /**
     * 序号
     */
    private Integer newcode;

    /**
     * 新房/二手房(0-新房，1-二手房)
     */
    private Short nOrE;

    /**
     * 楼盘名称
     */
    private String projname;

    /**
     * 别名
     */
    private String nickname;

    /**
     * 楼盘分期
     */
    private String installment;

    /**
     * 地址
     */
    private String address;

    /**
     * 地址描述
     */
    private String addressInfo;

    /**
     * 房源特色
     */
    private String housefeature;

    /**
     * 项目特色/标签(数组)
     */
    private String projFeature;

    /**
     * 省
     */
    private String province;

    /**
     * 城市
     */
    private Integer cityId;

    /**
     * 区县
     */
    private Integer districtId;

    /**
     * 商圈
     */
    private Integer areaId;

    /**
     * 环线
     */
    private String round;

    /**
     * 环线方位
     */
    private String rounddirection;

    /**
     * 所属社区
     */
    private Integer communityId;

    /**
     * 项目描述
     */
    private String projdesc;

    /**
     * 产权年限
     */
    private Short rightYear;

    /**
     * 占地面积(平方米)
     */
    private BigDecimal groundarea;

    /**
     * 建筑面积(平方米)
     */
    private BigDecimal purposearea;

    /**
     * 容积率(%)
     */
    private BigDecimal dimension;

    /**
     * 绿化率(%)
     */
    private BigDecimal virescencerate;

    /**
     * 楼层状况
     */
    private String buildingdes;

    /**
     * 总户数
     */
    private Integer totaldoor;

    /**
     * 停车位描述
     */
    private String parkdesc;

    /**
     * 停车位数量
     */
    private Integer parkspace;

    /**
     * 停车位租价
     */
    private BigDecimal carRentPrice;

    /**
     * 停车位售价
     */
    private BigDecimal carSellPrice;

    /**
     * 物业管理费
     */
    private BigDecimal propertyfee;

    /**
     * 物业管理费价格单位
     */
    private String propfeetype;

    /**
     * 物业管理费附加信息
     */
    private String propertyaddition;

    /**
     * 小区内部配套
     */
    private String layout;

    /**
     * 工程进度
     */
    private String workSchedule;

    /**
     * 开工时间
     */
    private Date startdate;

    /**
     * 竣工时间
     */
    private Date finishdate;

    /**
     * 开盘时间
     */
    private String saledate;

    /**
     * 开盘时间描述
     */
    private String saledateS;

    /**
     * 入住时间/交房时间
     */
    private String livindate;

    /**
     * 入住时间描述
     */
    private String livindateS;

    /**
     * 售楼热线
     */
    private String saletelphone;

    /**
     * 售楼地址
     */
    private String saleaddress;

    /**
     * 销售证
     */
    private String salecard;

    /**
     * 销售状态(0-售完,1-在售,2-不在售,3-出租,4-租售,5-待售)
     */
    private Short saling;

    /**
     * 销售阶段(0:期房.1:现房.2:尾房.3:二手房)
     */
    private Short sailSchedule;

    /**
     * 开发商
     */
    private String developer;

    /**
     * 投资商
     */
    private String investor;

    /**
     * 物业管理公司
     */
    private String propertymanage;

    /**
     * 承建商
     */
    private String construct;

    /**
     * 景观设计单位
     */
    private String sightdesign;

    /**
     * 审批状态（0-不通过，1-通过，2-错误）（0-未发布，1-已发布）
     */
    private Short isApprove;

    /**
     * 网站地址
     */
    private String webaddress;

    /**
     * 网上售楼中心地址
     */
    private String websaleaddress;

    /**
     * 完成度
     */
    private String completeFraction;

    /**
     * x坐标
     */
    private BigDecimal coordX;

    /**
     * y坐标
     */
    private BigDecimal coordY;

    /**
     * 楼盘置为售完的时间
     */
    private Date salingOptime;

    /**
     * 移交二手房或者从二手房转回的时间
     */
    private Date adminstautsTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 删除时间
     */
    private Date deldate;

    /**
     * 删除说明
     */
    private String delreason;

    /**
     * 价格增长率
     */
    private BigDecimal pricerate;

    /**
     * 最高价格
     */
    private BigDecimal pricemax;

    /**
     * 最低价格
     */
    private BigDecimal pricemin;

    /**
     * 名称全拼
     */
    private String pinyinName;

    /**
     * 名称拼音首字母
     */
    private String pinyinInitials;

    /**
     * 开盘更新时间
     */
    private Date updateSaledateTime;

    /**
     * 入住更新时间
     */
    private Date updateLivindateTime;

    /**
     * 产权描述
     */
    private String rightDesc;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 物业电话
     */
    private String propertyTele;

    /**
     * 物业地址
     */
    private String propertyAddress;

    /**
     * 创建人ID
     */
    private Integer creatorId;

    /**
     * 更新人ID
     */
    private Integer updaterId;

    /**
     * 删除标志位(0-否，1-是)
     */
    private Short isDel;

    /**
     * 平均单价-二手房
     */
    private BigDecimal esfPrice;

    /**
     * 平均总价-新房
     */
    private BigDecimal totalPrice;

    /**
     * 平均单价-新房
     */
    private BigDecimal price;

    /**
     * 平均总价-二手房
     */
    private BigDecimal esfTotalPrice;

    /**
     * 供暖方式(0-未知，1-集中供暖，2-自供暖）
     */
    private Integer heatingMode;

    /**
     * 梯户比
     */
    private String liftDoorRadio;

    /**
     * 车位配比
     */
    private String parkRadio;

    /**
     * 物业类别/业态：（1-住宅,2-别墅,3-写字楼，4-商铺，5-底商，6-住宅底商，7=办公别墅，8-标准写字楼，9-酒店写字楼，10-写字楼商铺，11-市场类商铺，12-商务型公寓，13-购物中心，14-企业独栋，15-商业，16-总部园区）
     */
    private Integer propertyType;

    /**
     * 住宅建筑形式(数组)：1-低层，2-多层，3-小高层，4-高层，5-超高层，6-联排、7-独栋、8-双拼、9-叠拼、10-空中花园、11-空中别墅、12-开间、13-平层、14-复式、15-跃层、16-其他、17-合院
     */
    private Object buildForm;

    /**
     * 建筑类别(数组)：1：板楼，2：塔楼，3：板塔结合，4：砖楼5：其他
     */
    private Object buildCategory;

    /**
     * 别墅建筑风格(数组)：1-中式、2-欧式、3-日式、4-美式、5-英式、6-澳式、7-法式、8-西班牙式、9-东南亚式、10-地中海式、11-意大利式、12-现代
     */
    private Object villaStyle;

    /**
     * 新房平均单价单位
     */
    private String priceUnit;

    /**
     * 新房平均总价单位
     */
    private String totalPriceUnit;

    /**
     * 二手房平均单价单位
     */
    private String esfPriceUnit;

    /**
     * 二手房平均总价单位
     */
    private String esfTotalPriceUnit;

    /**
     * 住宅类别(数组)：（1：普通住宅，2：公寓，3：酒店式公寓，4：花园洋房，5：商住楼，6：独栋别墅，7：联排别墅，8：经济适用房， 9：廉租房，10：公共租赁房，11：定向安置房，12：两限商品房，13：自住型商品房，14：其他，15-商铺，16-写字楼，17-平房，18-车位，19-办公，20-四合院，21-住宅商铺，22-写字楼商铺，23-商务型商铺，24-市场类商铺，25-标准写字楼，26-酒店写字楼，27-别墅，28-办公别墅，29-购物中心，30-商业，31-企业独栋，32-住宅底商，33-自持租赁，34-总部园区）
     */
    private Object residentialCategory;

    /**
     * 空气质量
     */
    private String airQuality;

    /**
     * 电梯配备(1-有，2-无)
     */
    private Short hasLift;

    /**
     * 活动信息
     */
    private Object activityInfo;

    /**
     * 销售许可证信息
     */
    private Object salesLicenseInfo;

    /**
     * 楼盘动态
     */
    private Object dynamicInfo;

    /**
     * 楼盘优惠
     */
    private String discountInfo;

    /**
     * 楼盘评级(1-广告/付费，2-优，3-良，4-一般)
     */
    private Short level;

    /**
     * 楼盘评分
     */
    private Integer score;

    /**
     * 楼栋数
     */
    private Integer buildCount;

    /**
     * 环路(1-二环以内,2-二至三环,3-三至四环,4-四至五环,5-五至六环,6-六环以外)
     */
    private Short ringRoad;

    /**
     * 供水
     */
    private String waterSupply;

    /**
     * 供电
     */
    private String electricSupply;

    /**
     * 交通信息
     */
    private String traffic;

    /**
     * 装修(数组)(1:毛坯 2:普通装修 3:精装修 4:豪华装修 5:其他 6:非毛坯 7:公共部分简单装修)
     */
    private Object fitment;

    /**
     * 楼盘标签
     */
    private Object buildTags;

    /**
     * 小区到环线主路的距离
     */
    private BigDecimal villageMainroad;

    /**
     * 新房面积范围（起始）
     */
    private BigDecimal newhRangeS;

    /**
     * 新房面积范围（结束）
     */
    private BigDecimal newhRangeE;

    /**
     * 小区面积范围（起始）
     */
    private BigDecimal villageRangeS;

    /**
     * 小区面积范围（结束）
     */
    private BigDecimal villageRangeE;

    /**
     * 供电
     */
    private String powerSupply;

    /**
     * 优惠信息
     */
    private String residentialHouseType;

    /**
     * 小区换手率
     */
    private BigDecimal turnoverRate;

    /**
     * 小区出租率
     */
    private BigDecimal rentals;

    /**
     * 小区图片
     */
    private String plotImage;

    /**
     * 区域名称
     */
    private String districtName;

    /**
     * 商圈名称
     */
    private String areaName;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 方圆1.0km内是否有地铁(1-有，2-无)
     */
    private Short hasSubway;

    /**
     * 是否有教育配套
     */
    private Short hasSchool;

    /**
     * 1.0km是否有购物配套
     */
    private Short hasMarket;

    /**
     * 附近地铁站
     */
    private String nearbyStation;

    /**
     * 附近学校
     */
    private String nearbySchool;

    /**
     * 附近购物配套
     */
    private String nearbyMarket;

    /**
     * 最小面积
     */
    private BigDecimal minArea;

    /**
     * 最大面积
     */
    private BigDecimal maxArea;

    /**
     * 1.0km是否有医疗配套
     */
    private Short hasMedical;

    /**
     * 附近学校类别数组
     */
    private Object schoolTypeArray;

    /**
     * 附近医疗配套
     */
    private String nearbyMedical;

    /**
     * 1.0km是否有餐厅(1-有，2-无)
     */
    private Short hasRestaurant;

    /**
     * 附近餐厅
     */
    private String nearbyRestaurant;

    /**
     * 1.0km是否有休闲设施(1-有，2-无)
     */
    private Short hasLeisure;

    /**
     * 附近休闲设施
     */
    private String nearbyLeisure;

    /**
     * 1.0km是否有健身设施(1-有，2-无)
     */
    private Short hasBodybuilding;

    /**
     * 附近健身设施
     */
    private String nearbyBodybuilding;

    /**
     * 附近符合条件地铁站个数
     */
    private Integer nearbyStationCount;

    /**
     * 附近符合条件购物中心个数
     */
    private Integer nearbyMarketCount;

    /**
     * 距离最近的环线
     */
    private String nearbyRoad;

    /**
     * 距离最近的环线米
     */
    private Integer nearbyRoadMeter;

    /**
     * 最低总价
     */
    private BigDecimal minTotalPrice;

    /**
     * 最高总价
     */
    private BigDecimal maxTotalPrice;

    /**
     * 楼盘包含的户型(数组)
     */
    private Object layoutRange;

    /**
     * 最近地铁站描述
     */
    private String nearestSubwayDesc;

    /**
     * 楼盘3km地铁线
     */
    private Object subwayLine;

    /**
     * 楼盘3km地铁站
     */
    private Object subwayStation;

    /**
     * 楼盘距地铁站的距离
     */
    private String subwayDistince;

    /**
     * 小区户型集合
     */
    private String projLayout;

    public Integer getNewcode() {
        return newcode;
    }

    public void setNewcode(Integer newcode) {
        this.newcode = newcode;
    }

    public Short getnOrE() {
        return nOrE;
    }

    public void setnOrE(Short nOrE) {
        this.nOrE = nOrE;
    }

    public String getProjname() {
        return projname;
    }

    public void setProjname(String projname) {
        this.projname = projname == null ? null : projname.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getInstallment() {
        return installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment == null ? null : installment.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo == null ? null : addressInfo.trim();
    }

    public String getHousefeature() {
        return housefeature;
    }

    public void setHousefeature(String housefeature) {
        this.housefeature = housefeature == null ? null : housefeature.trim();
    }

    public String getProjFeature() {
        return projFeature;
    }

    public void setProjFeature(String projFeature) {
        this.projFeature = projFeature == null ? null : projFeature.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round == null ? null : round.trim();
    }

    public String getRounddirection() {
        return rounddirection;
    }

    public void setRounddirection(String rounddirection) {
        this.rounddirection = rounddirection == null ? null : rounddirection.trim();
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getProjdesc() {
        return projdesc;
    }

    public void setProjdesc(String projdesc) {
        this.projdesc = projdesc == null ? null : projdesc.trim();
    }

    public Short getRightYear() {
        return rightYear;
    }

    public void setRightYear(Short rightYear) {
        this.rightYear = rightYear;
    }

    public BigDecimal getGroundarea() {
        return groundarea;
    }

    public void setGroundarea(BigDecimal groundarea) {
        this.groundarea = groundarea;
    }

    public BigDecimal getPurposearea() {
        return purposearea;
    }

    public void setPurposearea(BigDecimal purposearea) {
        this.purposearea = purposearea;
    }

    public BigDecimal getDimension() {
        return dimension;
    }

    public void setDimension(BigDecimal dimension) {
        this.dimension = dimension;
    }

    public BigDecimal getVirescencerate() {
        return virescencerate;
    }

    public void setVirescencerate(BigDecimal virescencerate) {
        this.virescencerate = virescencerate;
    }

    public String getBuildingdes() {
        return buildingdes;
    }

    public void setBuildingdes(String buildingdes) {
        this.buildingdes = buildingdes == null ? null : buildingdes.trim();
    }

    public Integer getTotaldoor() {
        return totaldoor;
    }

    public void setTotaldoor(Integer totaldoor) {
        this.totaldoor = totaldoor;
    }

    public String getParkdesc() {
        return parkdesc;
    }

    public void setParkdesc(String parkdesc) {
        this.parkdesc = parkdesc == null ? null : parkdesc.trim();
    }

    public Integer getParkspace() {
        return parkspace;
    }

    public void setParkspace(Integer parkspace) {
        this.parkspace = parkspace;
    }

    public BigDecimal getCarRentPrice() {
        return carRentPrice;
    }

    public void setCarRentPrice(BigDecimal carRentPrice) {
        this.carRentPrice = carRentPrice;
    }

    public BigDecimal getCarSellPrice() {
        return carSellPrice;
    }

    public void setCarSellPrice(BigDecimal carSellPrice) {
        this.carSellPrice = carSellPrice;
    }

    public BigDecimal getPropertyfee() {
        return propertyfee;
    }

    public void setPropertyfee(BigDecimal propertyfee) {
        this.propertyfee = propertyfee;
    }

    public String getPropfeetype() {
        return propfeetype;
    }

    public void setPropfeetype(String propfeetype) {
        this.propfeetype = propfeetype == null ? null : propfeetype.trim();
    }

    public String getPropertyaddition() {
        return propertyaddition;
    }

    public void setPropertyaddition(String propertyaddition) {
        this.propertyaddition = propertyaddition == null ? null : propertyaddition.trim();
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout == null ? null : layout.trim();
    }

    public String getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(String workSchedule) {
        this.workSchedule = workSchedule == null ? null : workSchedule.trim();
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getFinishdate() {
        return finishdate;
    }

    public void setFinishdate(Date finishdate) {
        this.finishdate = finishdate;
    }

    public String getSaledate() {
        return saledate;
    }

    public void setSaledate(String saledate) {
        this.saledate = saledate == null ? null : saledate.trim();
    }

    public String getSaledateS() {
        return saledateS;
    }

    public void setSaledateS(String saledateS) {
        this.saledateS = saledateS == null ? null : saledateS.trim();
    }

    public String getLivindate() {
        return livindate;
    }

    public void setLivindate(String livindate) {
        this.livindate = livindate == null ? null : livindate.trim();
    }

    public String getLivindateS() {
        return livindateS;
    }

    public void setLivindateS(String livindateS) {
        this.livindateS = livindateS == null ? null : livindateS.trim();
    }

    public String getSaletelphone() {
        return saletelphone;
    }

    public void setSaletelphone(String saletelphone) {
        this.saletelphone = saletelphone == null ? null : saletelphone.trim();
    }

    public String getSaleaddress() {
        return saleaddress;
    }

    public void setSaleaddress(String saleaddress) {
        this.saleaddress = saleaddress == null ? null : saleaddress.trim();
    }

    public String getSalecard() {
        return salecard;
    }

    public void setSalecard(String salecard) {
        this.salecard = salecard == null ? null : salecard.trim();
    }

    public Short getSaling() {
        return saling;
    }

    public void setSaling(Short saling) {
        this.saling = saling;
    }

    public Short getSailSchedule() {
        return sailSchedule;
    }

    public void setSailSchedule(Short sailSchedule) {
        this.sailSchedule = sailSchedule;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer == null ? null : developer.trim();
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor == null ? null : investor.trim();
    }

    public String getPropertymanage() {
        return propertymanage;
    }

    public void setPropertymanage(String propertymanage) {
        this.propertymanage = propertymanage == null ? null : propertymanage.trim();
    }

    public String getConstruct() {
        return construct;
    }

    public void setConstruct(String construct) {
        this.construct = construct == null ? null : construct.trim();
    }

    public String getSightdesign() {
        return sightdesign;
    }

    public void setSightdesign(String sightdesign) {
        this.sightdesign = sightdesign == null ? null : sightdesign.trim();
    }

    public Short getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Short isApprove) {
        this.isApprove = isApprove;
    }

    public String getWebaddress() {
        return webaddress;
    }

    public void setWebaddress(String webaddress) {
        this.webaddress = webaddress == null ? null : webaddress.trim();
    }

    public String getWebsaleaddress() {
        return websaleaddress;
    }

    public void setWebsaleaddress(String websaleaddress) {
        this.websaleaddress = websaleaddress == null ? null : websaleaddress.trim();
    }

    public String getCompleteFraction() {
        return completeFraction;
    }

    public void setCompleteFraction(String completeFraction) {
        this.completeFraction = completeFraction == null ? null : completeFraction.trim();
    }

    public BigDecimal getCoordX() {
        return coordX;
    }

    public void setCoordX(BigDecimal coordX) {
        this.coordX = coordX;
    }

    public BigDecimal getCoordY() {
        return coordY;
    }

    public void setCoordY(BigDecimal coordY) {
        this.coordY = coordY;
    }

    public Date getSalingOptime() {
        return salingOptime;
    }

    public void setSalingOptime(Date salingOptime) {
        this.salingOptime = salingOptime;
    }

    public Date getAdminstautsTime() {
        return adminstautsTime;
    }

    public void setAdminstautsTime(Date adminstautsTime) {
        this.adminstautsTime = adminstautsTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeldate() {
        return deldate;
    }

    public void setDeldate(Date deldate) {
        this.deldate = deldate;
    }

    public String getDelreason() {
        return delreason;
    }

    public void setDelreason(String delreason) {
        this.delreason = delreason == null ? null : delreason.trim();
    }

    public BigDecimal getPricerate() {
        return pricerate;
    }

    public void setPricerate(BigDecimal pricerate) {
        this.pricerate = pricerate;
    }

    public BigDecimal getPricemax() {
        return pricemax;
    }

    public void setPricemax(BigDecimal pricemax) {
        this.pricemax = pricemax;
    }

    public BigDecimal getPricemin() {
        return pricemin;
    }

    public void setPricemin(BigDecimal pricemin) {
        this.pricemin = pricemin;
    }

    public String getPinyinName() {
        return pinyinName;
    }

    public void setPinyinName(String pinyinName) {
        this.pinyinName = pinyinName == null ? null : pinyinName.trim();
    }

    public String getPinyinInitials() {
        return pinyinInitials;
    }

    public void setPinyinInitials(String pinyinInitials) {
        this.pinyinInitials = pinyinInitials == null ? null : pinyinInitials.trim();
    }

    public Date getUpdateSaledateTime() {
        return updateSaledateTime;
    }

    public void setUpdateSaledateTime(Date updateSaledateTime) {
        this.updateSaledateTime = updateSaledateTime;
    }

    public Date getUpdateLivindateTime() {
        return updateLivindateTime;
    }

    public void setUpdateLivindateTime(Date updateLivindateTime) {
        this.updateLivindateTime = updateLivindateTime;
    }

    public String getRightDesc() {
        return rightDesc;
    }

    public void setRightDesc(String rightDesc) {
        this.rightDesc = rightDesc == null ? null : rightDesc.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getPropertyTele() {
        return propertyTele;
    }

    public void setPropertyTele(String propertyTele) {
        this.propertyTele = propertyTele == null ? null : propertyTele.trim();
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress == null ? null : propertyAddress.trim();
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

    public Short getIsDel() {
        return isDel;
    }

    public void setIsDel(Short isDel) {
        this.isDel = isDel;
    }

    public BigDecimal getEsfPrice() {
        return esfPrice;
    }

    public void setEsfPrice(BigDecimal esfPrice) {
        this.esfPrice = esfPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getEsfTotalPrice() {
        return esfTotalPrice;
    }

    public void setEsfTotalPrice(BigDecimal esfTotalPrice) {
        this.esfTotalPrice = esfTotalPrice;
    }

    public Integer getHeatingMode() {
        return heatingMode;
    }

    public void setHeatingMode(Integer heatingMode) {
        this.heatingMode = heatingMode;
    }

    public String getLiftDoorRadio() {
        return liftDoorRadio;
    }

    public void setLiftDoorRadio(String liftDoorRadio) {
        this.liftDoorRadio = liftDoorRadio == null ? null : liftDoorRadio.trim();
    }

    public String getParkRadio() {
        return parkRadio;
    }

    public void setParkRadio(String parkRadio) {
        this.parkRadio = parkRadio == null ? null : parkRadio.trim();
    }

    public Integer getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(Integer propertyType) {
        this.propertyType = propertyType;
    }

    public Object getBuildForm() {
        return buildForm;
    }

    public void setBuildForm(Object buildForm) {
        this.buildForm = buildForm;
    }

    public Object getBuildCategory() {
        return buildCategory;
    }

    public void setBuildCategory(Object buildCategory) {
        this.buildCategory = buildCategory;
    }

    public Object getVillaStyle() {
        return villaStyle;
    }

    public void setVillaStyle(Object villaStyle) {
        this.villaStyle = villaStyle;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit == null ? null : priceUnit.trim();
    }

    public String getTotalPriceUnit() {
        return totalPriceUnit;
    }

    public void setTotalPriceUnit(String totalPriceUnit) {
        this.totalPriceUnit = totalPriceUnit == null ? null : totalPriceUnit.trim();
    }

    public String getEsfPriceUnit() {
        return esfPriceUnit;
    }

    public void setEsfPriceUnit(String esfPriceUnit) {
        this.esfPriceUnit = esfPriceUnit == null ? null : esfPriceUnit.trim();
    }

    public String getEsfTotalPriceUnit() {
        return esfTotalPriceUnit;
    }

    public void setEsfTotalPriceUnit(String esfTotalPriceUnit) {
        this.esfTotalPriceUnit = esfTotalPriceUnit == null ? null : esfTotalPriceUnit.trim();
    }

    public Object getResidentialCategory() {
        return residentialCategory;
    }

    public void setResidentialCategory(Object residentialCategory) {
        this.residentialCategory = residentialCategory;
    }

    public String getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(String airQuality) {
        this.airQuality = airQuality == null ? null : airQuality.trim();
    }

    public Short getHasLift() {
        return hasLift;
    }

    public void setHasLift(Short hasLift) {
        this.hasLift = hasLift;
    }

    public Object getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(Object activityInfo) {
        this.activityInfo = activityInfo;
    }

    public Object getSalesLicenseInfo() {
        return salesLicenseInfo;
    }

    public void setSalesLicenseInfo(Object salesLicenseInfo) {
        this.salesLicenseInfo = salesLicenseInfo;
    }

    public Object getDynamicInfo() {
        return dynamicInfo;
    }

    public void setDynamicInfo(Object dynamicInfo) {
        this.dynamicInfo = dynamicInfo;
    }

    public String getDiscountInfo() {
        return discountInfo;
    }

    public void setDiscountInfo(String discountInfo) {
        this.discountInfo = discountInfo == null ? null : discountInfo.trim();
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getBuildCount() {
        return buildCount;
    }

    public void setBuildCount(Integer buildCount) {
        this.buildCount = buildCount;
    }

    public Short getRingRoad() {
        return ringRoad;
    }

    public void setRingRoad(Short ringRoad) {
        this.ringRoad = ringRoad;
    }

    public String getWaterSupply() {
        return waterSupply;
    }

    public void setWaterSupply(String waterSupply) {
        this.waterSupply = waterSupply == null ? null : waterSupply.trim();
    }

    public String getElectricSupply() {
        return electricSupply;
    }

    public void setElectricSupply(String electricSupply) {
        this.electricSupply = electricSupply == null ? null : electricSupply.trim();
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic == null ? null : traffic.trim();
    }

    public Object getFitment() {
        return fitment;
    }

    public void setFitment(Object fitment) {
        this.fitment = fitment;
    }

    public Object getBuildTags() {
        return buildTags;
    }

    public void setBuildTags(Object buildTags) {
        this.buildTags = buildTags;
    }

    public BigDecimal getVillageMainroad() {
        return villageMainroad;
    }

    public void setVillageMainroad(BigDecimal villageMainroad) {
        this.villageMainroad = villageMainroad;
    }

    public BigDecimal getNewhRangeS() {
        return newhRangeS;
    }

    public void setNewhRangeS(BigDecimal newhRangeS) {
        this.newhRangeS = newhRangeS;
    }

    public BigDecimal getNewhRangeE() {
        return newhRangeE;
    }

    public void setNewhRangeE(BigDecimal newhRangeE) {
        this.newhRangeE = newhRangeE;
    }

    public BigDecimal getVillageRangeS() {
        return villageRangeS;
    }

    public void setVillageRangeS(BigDecimal villageRangeS) {
        this.villageRangeS = villageRangeS;
    }

    public BigDecimal getVillageRangeE() {
        return villageRangeE;
    }

    public void setVillageRangeE(BigDecimal villageRangeE) {
        this.villageRangeE = villageRangeE;
    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(String powerSupply) {
        this.powerSupply = powerSupply == null ? null : powerSupply.trim();
    }

    public String getResidentialHouseType() {
        return residentialHouseType;
    }

    public void setResidentialHouseType(String residentialHouseType) {
        this.residentialHouseType = residentialHouseType == null ? null : residentialHouseType.trim();
    }

    public BigDecimal getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(BigDecimal turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public BigDecimal getRentals() {
        return rentals;
    }

    public void setRentals(BigDecimal rentals) {
        this.rentals = rentals;
    }

    public String getPlotImage() {
        return plotImage;
    }

    public void setPlotImage(String plotImage) {
        this.plotImage = plotImage == null ? null : plotImage.trim();
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Short getHasSubway() {
        return hasSubway;
    }

    public void setHasSubway(Short hasSubway) {
        this.hasSubway = hasSubway;
    }

    public Short getHasSchool() {
        return hasSchool;
    }

    public void setHasSchool(Short hasSchool) {
        this.hasSchool = hasSchool;
    }

    public Short getHasMarket() {
        return hasMarket;
    }

    public void setHasMarket(Short hasMarket) {
        this.hasMarket = hasMarket;
    }

    public String getNearbyStation() {
        return nearbyStation;
    }

    public void setNearbyStation(String nearbyStation) {
        this.nearbyStation = nearbyStation == null ? null : nearbyStation.trim();
    }

    public String getNearbySchool() {
        return nearbySchool;
    }

    public void setNearbySchool(String nearbySchool) {
        this.nearbySchool = nearbySchool == null ? null : nearbySchool.trim();
    }

    public String getNearbyMarket() {
        return nearbyMarket;
    }

    public void setNearbyMarket(String nearbyMarket) {
        this.nearbyMarket = nearbyMarket == null ? null : nearbyMarket.trim();
    }

    public BigDecimal getMinArea() {
        return minArea;
    }

    public void setMinArea(BigDecimal minArea) {
        this.minArea = minArea;
    }

    public BigDecimal getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(BigDecimal maxArea) {
        this.maxArea = maxArea;
    }

    public Short getHasMedical() {
        return hasMedical;
    }

    public void setHasMedical(Short hasMedical) {
        this.hasMedical = hasMedical;
    }

    public Object getSchoolTypeArray() {
        return schoolTypeArray;
    }

    public void setSchoolTypeArray(Object schoolTypeArray) {
        this.schoolTypeArray = schoolTypeArray;
    }

    public String getNearbyMedical() {
        return nearbyMedical;
    }

    public void setNearbyMedical(String nearbyMedical) {
        this.nearbyMedical = nearbyMedical == null ? null : nearbyMedical.trim();
    }

    public Short getHasRestaurant() {
        return hasRestaurant;
    }

    public void setHasRestaurant(Short hasRestaurant) {
        this.hasRestaurant = hasRestaurant;
    }

    public String getNearbyRestaurant() {
        return nearbyRestaurant;
    }

    public void setNearbyRestaurant(String nearbyRestaurant) {
        this.nearbyRestaurant = nearbyRestaurant == null ? null : nearbyRestaurant.trim();
    }

    public Short getHasLeisure() {
        return hasLeisure;
    }

    public void setHasLeisure(Short hasLeisure) {
        this.hasLeisure = hasLeisure;
    }

    public String getNearbyLeisure() {
        return nearbyLeisure;
    }

    public void setNearbyLeisure(String nearbyLeisure) {
        this.nearbyLeisure = nearbyLeisure == null ? null : nearbyLeisure.trim();
    }

    public Short getHasBodybuilding() {
        return hasBodybuilding;
    }

    public void setHasBodybuilding(Short hasBodybuilding) {
        this.hasBodybuilding = hasBodybuilding;
    }

    public String getNearbyBodybuilding() {
        return nearbyBodybuilding;
    }

    public void setNearbyBodybuilding(String nearbyBodybuilding) {
        this.nearbyBodybuilding = nearbyBodybuilding == null ? null : nearbyBodybuilding.trim();
    }

    public Integer getNearbyStationCount() {
        return nearbyStationCount;
    }

    public void setNearbyStationCount(Integer nearbyStationCount) {
        this.nearbyStationCount = nearbyStationCount;
    }

    public Integer getNearbyMarketCount() {
        return nearbyMarketCount;
    }

    public void setNearbyMarketCount(Integer nearbyMarketCount) {
        this.nearbyMarketCount = nearbyMarketCount;
    }

    public String getNearbyRoad() {
        return nearbyRoad;
    }

    public void setNearbyRoad(String nearbyRoad) {
        this.nearbyRoad = nearbyRoad == null ? null : nearbyRoad.trim();
    }

    public Integer getNearbyRoadMeter() {
        return nearbyRoadMeter;
    }

    public void setNearbyRoadMeter(Integer nearbyRoadMeter) {
        this.nearbyRoadMeter = nearbyRoadMeter;
    }

    public BigDecimal getMinTotalPrice() {
        return minTotalPrice;
    }

    public void setMinTotalPrice(BigDecimal minTotalPrice) {
        this.minTotalPrice = minTotalPrice;
    }

    public BigDecimal getMaxTotalPrice() {
        return maxTotalPrice;
    }

    public void setMaxTotalPrice(BigDecimal maxTotalPrice) {
        this.maxTotalPrice = maxTotalPrice;
    }

    public Object getLayoutRange() {
        return layoutRange;
    }

    public void setLayoutRange(Object layoutRange) {
        this.layoutRange = layoutRange;
    }

    public String getNearestSubwayDesc() {
        return nearestSubwayDesc;
    }

    public void setNearestSubwayDesc(String nearestSubwayDesc) {
        this.nearestSubwayDesc = nearestSubwayDesc == null ? null : nearestSubwayDesc.trim();
    }

    public Object getSubwayLine() {
        return subwayLine;
    }

    public void setSubwayLine(Object subwayLine) {
        this.subwayLine = subwayLine;
    }

    public Object getSubwayStation() {
        return subwayStation;
    }

    public void setSubwayStation(Object subwayStation) {
        this.subwayStation = subwayStation;
    }

    public String getSubwayDistince() {
        return subwayDistince;
    }

    public void setSubwayDistince(String subwayDistince) {
        this.subwayDistince = subwayDistince == null ? null : subwayDistince.trim();
    }

    public String getProjLayout() {
        return projLayout;
    }

    public void setProjLayout(String projLayout) {
        this.projLayout = projLayout == null ? null : projLayout.trim();
    }
}