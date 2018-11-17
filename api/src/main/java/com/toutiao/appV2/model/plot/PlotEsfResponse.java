package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.*;

/**
 * PlotEsfResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class PlotEsfResponse   {
  @JsonProperty("agentBaseDo")
  private AgentBaseDo agentBaseDo = null;

  @JsonProperty("area")
  private String area = null;

  @JsonProperty("areaId")
  private Integer areaId = null;

  @JsonProperty("avgAbsoluteWithBizcircle")
  private Double avgAbsoluteWithBizcircle = null;

  @JsonProperty("avgAbsoluteWithCommunity")
  private Double avgAbsoluteWithCommunity = null;

  @JsonProperty("avgAbsoluteWithDistrict")
  private Double avgAbsoluteWithDistrict = null;

  @JsonProperty("avgDealCycle")
  private Integer avgDealCycle = null;

  @JsonProperty("avgRelativeWithBizcircle")
  private Double avgRelativeWithBizcircle = null;

  @JsonProperty("avgRelativeWithCommunity")
  private Double avgRelativeWithCommunity = null;

  @JsonProperty("avgRelativeWithDistrict")
  private Double avgRelativeWithDistrict = null;

  @JsonProperty("buildArea")
  private Double buildArea = null;

  @JsonProperty("forwardName")
  private String forwardName = null;

  @JsonProperty("hall")
  private Integer hall = null;

  @JsonProperty("houseBusinessName")
  private String houseBusinessName = null;

  @JsonProperty("houseBusinessNameId")
  private String houseBusinessNameId = null;

  @JsonProperty("houseId")
  private String houseId = null;

  @JsonProperty("housePhotoTitle")
  private String housePhotoTitle = null;

  @JsonProperty("houseTitle")
  private String houseTitle = null;

  @JsonProperty("houseTotalPrices")
  private Double houseTotalPrices = null;

  @JsonProperty("houseUnitCost")
  private Double houseUnitCost = null;

  @JsonProperty("importTime")
  private String importTime = null;

  @JsonProperty("isCommunityTopHouse")
  private Integer isCommunityTopHouse = null;

  @JsonProperty("isCutPrice")
  private Integer isCutPrice = null;

  @JsonProperty("isDealLayout")
  private Integer isDealLayout = null;

  @JsonProperty("isDefaultImage")
  private Integer isDefaultImage = null;

  @JsonProperty("isLowPrice")
  private Integer isLowPrice = null;

  @JsonProperty("isLowest")
  private Integer isLowest = null;

  @JsonProperty("isMainLayout")
  private Integer isMainLayout = null;

  @JsonProperty("isMustRob")
  private Integer isMustRob = null;

  @JsonProperty("isNew")
  private Integer isNew = null;

  @JsonProperty("nearPark")
  private String nearPark = null;

  @JsonProperty("newCode")
  private Integer newCode = null;

  @JsonProperty("plotName")
  private String plotName = null;

  @JsonProperty("priceFloat")
  private Double priceFloat = null;

  @JsonProperty("rankInLowCommunityLayout")
  private Integer rankInLowCommunityLayout = null;

  @JsonProperty("rankLowInBizcircleLayout")
  private Integer rankLowInBizcircleLayout = null;

  @JsonProperty("recommendBuildTagsId")
  @Valid
  private List<Object> recommendBuildTagsId = null;

  @JsonProperty("recommendBuildTagsName")
  @Valid
  private List<Object> recommendBuildTagsName = null;

  @JsonProperty("room")
  private Integer room = null;

  @JsonProperty("tagsName")
  @Valid
  private List<String> tagsName = null;

  @JsonProperty("totalAbsoluteWithBizcircle")
  private Double totalAbsoluteWithBizcircle = null;

  @JsonProperty("totalAbsoluteWithCommunity")
  private Double totalAbsoluteWithCommunity = null;

  @JsonProperty("totalAbsoluteWithDistrict")
  private Double totalAbsoluteWithDistrict = null;

  @JsonProperty("totalRelativeWithBizcircle")
  private Double totalRelativeWithBizcircle = null;

  @JsonProperty("totalRelativeWithCommunity")
  private Double totalRelativeWithCommunity = null;

  @JsonProperty("totalRelativeWithDistrict")
  private Double totalRelativeWithDistrict = null;

  @JsonProperty("typeCounts")
  @Valid
  private Map<String, Map<String, Integer>> typeCounts = null;

  public PlotEsfResponse agentBaseDo(AgentBaseDo agentBaseDo) {
    this.agentBaseDo = agentBaseDo;
    return this;
  }

  /**
   * Get agentBaseDo
   * @return agentBaseDo
  **/
  @ApiModelProperty(value = "")

  @Valid

  public AgentBaseDo getAgentBaseDo() {
    return agentBaseDo;
  }

  public void setAgentBaseDo(AgentBaseDo agentBaseDo) {
    this.agentBaseDo = agentBaseDo;
  }

  public PlotEsfResponse area(String area) {
    this.area = area;
    return this;
  }

  /**
   * Get area
   * @return area
  **/
  @ApiModelProperty(value = "")


  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public PlotEsfResponse areaId(Integer areaId) {
    this.areaId = areaId;
    return this;
  }

  /**
   * Get areaId
   * @return areaId
  **/
  @ApiModelProperty(value = "")


  public Integer getAreaId() {
    return areaId;
  }

  public void setAreaId(Integer areaId) {
    this.areaId = areaId;
  }

  public PlotEsfResponse avgAbsoluteWithBizcircle(Double avgAbsoluteWithBizcircle) {
    this.avgAbsoluteWithBizcircle = avgAbsoluteWithBizcircle;
    return this;
  }

  /**
   * Get avgAbsoluteWithBizcircle
   * @return avgAbsoluteWithBizcircle
  **/
  @ApiModelProperty(value = "")


  public Double getAvgAbsoluteWithBizcircle() {
    return avgAbsoluteWithBizcircle;
  }

  public void setAvgAbsoluteWithBizcircle(Double avgAbsoluteWithBizcircle) {
    this.avgAbsoluteWithBizcircle = avgAbsoluteWithBizcircle;
  }

  public PlotEsfResponse avgAbsoluteWithCommunity(Double avgAbsoluteWithCommunity) {
    this.avgAbsoluteWithCommunity = avgAbsoluteWithCommunity;
    return this;
  }

  /**
   * Get avgAbsoluteWithCommunity
   * @return avgAbsoluteWithCommunity
  **/
  @ApiModelProperty(value = "")


  public Double getAvgAbsoluteWithCommunity() {
    return avgAbsoluteWithCommunity;
  }

  public void setAvgAbsoluteWithCommunity(Double avgAbsoluteWithCommunity) {
    this.avgAbsoluteWithCommunity = avgAbsoluteWithCommunity;
  }

  public PlotEsfResponse avgAbsoluteWithDistrict(Double avgAbsoluteWithDistrict) {
    this.avgAbsoluteWithDistrict = avgAbsoluteWithDistrict;
    return this;
  }

  /**
   * Get avgAbsoluteWithDistrict
   * @return avgAbsoluteWithDistrict
  **/
  @ApiModelProperty(value = "")


  public Double getAvgAbsoluteWithDistrict() {
    return avgAbsoluteWithDistrict;
  }

  public void setAvgAbsoluteWithDistrict(Double avgAbsoluteWithDistrict) {
    this.avgAbsoluteWithDistrict = avgAbsoluteWithDistrict;
  }

  public PlotEsfResponse avgDealCycle(Integer avgDealCycle) {
    this.avgDealCycle = avgDealCycle;
    return this;
  }

  /**
   * Get avgDealCycle
   * @return avgDealCycle
  **/
  @ApiModelProperty(value = "")


  public Integer getAvgDealCycle() {
    return avgDealCycle;
  }

  public void setAvgDealCycle(Integer avgDealCycle) {
    this.avgDealCycle = avgDealCycle;
  }

  public PlotEsfResponse avgRelativeWithBizcircle(Double avgRelativeWithBizcircle) {
    this.avgRelativeWithBizcircle = avgRelativeWithBizcircle;
    return this;
  }

  /**
   * Get avgRelativeWithBizcircle
   * @return avgRelativeWithBizcircle
  **/
  @ApiModelProperty(value = "")


  public Double getAvgRelativeWithBizcircle() {
    return avgRelativeWithBizcircle;
  }

  public void setAvgRelativeWithBizcircle(Double avgRelativeWithBizcircle) {
    this.avgRelativeWithBizcircle = avgRelativeWithBizcircle;
  }

  public PlotEsfResponse avgRelativeWithCommunity(Double avgRelativeWithCommunity) {
    this.avgRelativeWithCommunity = avgRelativeWithCommunity;
    return this;
  }

  /**
   * Get avgRelativeWithCommunity
   * @return avgRelativeWithCommunity
  **/
  @ApiModelProperty(value = "")


  public Double getAvgRelativeWithCommunity() {
    return avgRelativeWithCommunity;
  }

  public void setAvgRelativeWithCommunity(Double avgRelativeWithCommunity) {
    this.avgRelativeWithCommunity = avgRelativeWithCommunity;
  }

  public PlotEsfResponse avgRelativeWithDistrict(Double avgRelativeWithDistrict) {
    this.avgRelativeWithDistrict = avgRelativeWithDistrict;
    return this;
  }

  /**
   * Get avgRelativeWithDistrict
   * @return avgRelativeWithDistrict
  **/
  @ApiModelProperty(value = "")


  public Double getAvgRelativeWithDistrict() {
    return avgRelativeWithDistrict;
  }

  public void setAvgRelativeWithDistrict(Double avgRelativeWithDistrict) {
    this.avgRelativeWithDistrict = avgRelativeWithDistrict;
  }

  public PlotEsfResponse buildArea(Double buildArea) {
    this.buildArea = buildArea;
    return this;
  }

  /**
   * Get buildArea
   * @return buildArea
  **/
  @ApiModelProperty(value = "")


  public Double getBuildArea() {
    return buildArea;
  }

  public void setBuildArea(Double buildArea) {
    this.buildArea = buildArea;
  }

  public PlotEsfResponse forwardName(String forwardName) {
    this.forwardName = forwardName;
    return this;
  }

  /**
   * Get forwardName
   * @return forwardName
  **/
  @ApiModelProperty(value = "")


  public String getForwardName() {
    return forwardName;
  }

  public void setForwardName(String forwardName) {
    this.forwardName = forwardName;
  }

  public PlotEsfResponse hall(Integer hall) {
    this.hall = hall;
    return this;
  }

  /**
   * Get hall
   * @return hall
  **/
  @ApiModelProperty(value = "")


  public Integer getHall() {
    return hall;
  }

  public void setHall(Integer hall) {
    this.hall = hall;
  }

  public PlotEsfResponse houseBusinessName(String houseBusinessName) {
    this.houseBusinessName = houseBusinessName;
    return this;
  }

  /**
   * Get houseBusinessName
   * @return houseBusinessName
  **/
  @ApiModelProperty(value = "")


  public String getHouseBusinessName() {
    return houseBusinessName;
  }

  public void setHouseBusinessName(String houseBusinessName) {
    this.houseBusinessName = houseBusinessName;
  }

  public PlotEsfResponse houseBusinessNameId(String houseBusinessNameId) {
    this.houseBusinessNameId = houseBusinessNameId;
    return this;
  }

  /**
   * Get houseBusinessNameId
   * @return houseBusinessNameId
  **/
  @ApiModelProperty(value = "")


  public String getHouseBusinessNameId() {
    return houseBusinessNameId;
  }

  public void setHouseBusinessNameId(String houseBusinessNameId) {
    this.houseBusinessNameId = houseBusinessNameId;
  }

  public PlotEsfResponse houseId(String houseId) {
    this.houseId = houseId;
    return this;
  }

  /**
   * Get houseId
   * @return houseId
  **/
  @ApiModelProperty(value = "")


  public String getHouseId() {
    return houseId;
  }

  public void setHouseId(String houseId) {
    this.houseId = houseId;
  }

  public PlotEsfResponse housePhotoTitle(String housePhotoTitle) {
    this.housePhotoTitle = housePhotoTitle;
    return this;
  }

  /**
   * Get housePhotoTitle
   * @return housePhotoTitle
  **/
  @ApiModelProperty(value = "")


  public String getHousePhotoTitle() {
    return housePhotoTitle;
  }

  public void setHousePhotoTitle(String housePhotoTitle) {
    this.housePhotoTitle = housePhotoTitle;
  }

  public PlotEsfResponse houseTitle(String houseTitle) {
    this.houseTitle = houseTitle;
    return this;
  }

  /**
   * Get houseTitle
   * @return houseTitle
  **/
  @ApiModelProperty(value = "")


  public String getHouseTitle() {
    return houseTitle;
  }

  public void setHouseTitle(String houseTitle) {
    this.houseTitle = houseTitle;
  }

  public PlotEsfResponse houseTotalPrices(Double houseTotalPrices) {
    this.houseTotalPrices = houseTotalPrices;
    return this;
  }

  /**
   * Get houseTotalPrices
   * @return houseTotalPrices
  **/
  @ApiModelProperty(value = "")


  public Double getHouseTotalPrices() {
    return houseTotalPrices;
  }

  public void setHouseTotalPrices(Double houseTotalPrices) {
    this.houseTotalPrices = houseTotalPrices;
  }

  public PlotEsfResponse houseUnitCost(Double houseUnitCost) {
    this.houseUnitCost = houseUnitCost;
    return this;
  }

  /**
   * Get houseUnitCost
   * @return houseUnitCost
  **/
  @ApiModelProperty(value = "")


  public Double getHouseUnitCost() {
    return houseUnitCost;
  }

  public void setHouseUnitCost(Double houseUnitCost) {
    this.houseUnitCost = houseUnitCost;
  }

  public PlotEsfResponse importTime(String importTime) {
    this.importTime = importTime;
    return this;
  }

  /**
   * Get importTime
   * @return importTime
  **/
  @ApiModelProperty(value = "")


  public String getImportTime() {
    return importTime;
  }

  public void setImportTime(String importTime) {
    this.importTime = importTime;
  }

  public PlotEsfResponse isCommunityTopHouse(Integer isCommunityTopHouse) {
    this.isCommunityTopHouse = isCommunityTopHouse;
    return this;
  }

  /**
   * Get isCommunityTopHouse
   * @return isCommunityTopHouse
  **/
  @ApiModelProperty(value = "")


  public Integer getIsCommunityTopHouse() {
    return isCommunityTopHouse;
  }

  public void setIsCommunityTopHouse(Integer isCommunityTopHouse) {
    this.isCommunityTopHouse = isCommunityTopHouse;
  }

  public PlotEsfResponse isCutPrice(Integer isCutPrice) {
    this.isCutPrice = isCutPrice;
    return this;
  }

  /**
   * Get isCutPrice
   * @return isCutPrice
  **/
  @ApiModelProperty(value = "")


  public Integer getIsCutPrice() {
    return isCutPrice;
  }

  public void setIsCutPrice(Integer isCutPrice) {
    this.isCutPrice = isCutPrice;
  }

  public PlotEsfResponse isDealLayout(Integer isDealLayout) {
    this.isDealLayout = isDealLayout;
    return this;
  }

  /**
   * Get isDealLayout
   * @return isDealLayout
  **/
  @ApiModelProperty(value = "")


  public Integer getIsDealLayout() {
    return isDealLayout;
  }

  public void setIsDealLayout(Integer isDealLayout) {
    this.isDealLayout = isDealLayout;
  }

  public PlotEsfResponse isDefaultImage(Integer isDefaultImage) {
    this.isDefaultImage = isDefaultImage;
    return this;
  }

  /**
   * Get isDefaultImage
   * @return isDefaultImage
  **/
  @ApiModelProperty(value = "")


  public Integer getIsDefaultImage() {
    return isDefaultImage;
  }

  public void setIsDefaultImage(Integer isDefaultImage) {
    this.isDefaultImage = isDefaultImage;
  }

  public PlotEsfResponse isLowPrice(Integer isLowPrice) {
    this.isLowPrice = isLowPrice;
    return this;
  }

  /**
   * Get isLowPrice
   * @return isLowPrice
  **/
  @ApiModelProperty(value = "")


  public Integer getIsLowPrice() {
    return isLowPrice;
  }

  public void setIsLowPrice(Integer isLowPrice) {
    this.isLowPrice = isLowPrice;
  }

  public PlotEsfResponse isLowest(Integer isLowest) {
    this.isLowest = isLowest;
    return this;
  }

  /**
   * Get isLowest
   * @return isLowest
  **/
  @ApiModelProperty(value = "")


  public Integer getIsLowest() {
    return isLowest;
  }

  public void setIsLowest(Integer isLowest) {
    this.isLowest = isLowest;
  }

  public PlotEsfResponse isMainLayout(Integer isMainLayout) {
    this.isMainLayout = isMainLayout;
    return this;
  }

  /**
   * Get isMainLayout
   * @return isMainLayout
  **/
  @ApiModelProperty(value = "")


  public Integer getIsMainLayout() {
    return isMainLayout;
  }

  public void setIsMainLayout(Integer isMainLayout) {
    this.isMainLayout = isMainLayout;
  }

  public PlotEsfResponse isMustRob(Integer isMustRob) {
    this.isMustRob = isMustRob;
    return this;
  }

  /**
   * Get isMustRob
   * @return isMustRob
  **/
  @ApiModelProperty(value = "")


  public Integer getIsMustRob() {
    return isMustRob;
  }

  public void setIsMustRob(Integer isMustRob) {
    this.isMustRob = isMustRob;
  }

  public PlotEsfResponse isNew(Integer isNew) {
    this.isNew = isNew;
    return this;
  }

  /**
   * Get isNew
   * @return isNew
  **/
  @ApiModelProperty(value = "")


  public Integer getIsNew() {
    return isNew;
  }

  public void setIsNew(Integer isNew) {
    this.isNew = isNew;
  }

  public PlotEsfResponse nearPark(String nearPark) {
    this.nearPark = nearPark;
    return this;
  }

  /**
   * Get nearPark
   * @return nearPark
  **/
  @ApiModelProperty(value = "")


  public String getNearPark() {
    return nearPark;
  }

  public void setNearPark(String nearPark) {
    this.nearPark = nearPark;
  }

  public PlotEsfResponse newCode(Integer newCode) {
    this.newCode = newCode;
    return this;
  }

  /**
   * Get newCode
   * @return newCode
  **/
  @ApiModelProperty(value = "")


  public Integer getNewCode() {
    return newCode;
  }

  public void setNewCode(Integer newCode) {
    this.newCode = newCode;
  }

  public PlotEsfResponse plotName(String plotName) {
    this.plotName = plotName;
    return this;
  }

  /**
   * Get plotName
   * @return plotName
  **/
  @ApiModelProperty(value = "")


  public String getPlotName() {
    return plotName;
  }

  public void setPlotName(String plotName) {
    this.plotName = plotName;
  }

  public PlotEsfResponse priceFloat(Double priceFloat) {
    this.priceFloat = priceFloat;
    return this;
  }

  /**
   * Get priceFloat
   * @return priceFloat
  **/
  @ApiModelProperty(value = "")


  public Double getPriceFloat() {
    return priceFloat;
  }

  public void setPriceFloat(Double priceFloat) {
    this.priceFloat = priceFloat;
  }

  public PlotEsfResponse rankInLowCommunityLayout(Integer rankInLowCommunityLayout) {
    this.rankInLowCommunityLayout = rankInLowCommunityLayout;
    return this;
  }

  /**
   * Get rankInLowCommunityLayout
   * @return rankInLowCommunityLayout
  **/
  @ApiModelProperty(value = "")


  public Integer getRankInLowCommunityLayout() {
    return rankInLowCommunityLayout;
  }

  public void setRankInLowCommunityLayout(Integer rankInLowCommunityLayout) {
    this.rankInLowCommunityLayout = rankInLowCommunityLayout;
  }

  public PlotEsfResponse rankLowInBizcircleLayout(Integer rankLowInBizcircleLayout) {
    this.rankLowInBizcircleLayout = rankLowInBizcircleLayout;
    return this;
  }

  /**
   * Get rankLowInBizcircleLayout
   * @return rankLowInBizcircleLayout
  **/
  @ApiModelProperty(value = "")


  public Integer getRankLowInBizcircleLayout() {
    return rankLowInBizcircleLayout;
  }

  public void setRankLowInBizcircleLayout(Integer rankLowInBizcircleLayout) {
    this.rankLowInBizcircleLayout = rankLowInBizcircleLayout;
  }

  public PlotEsfResponse recommendBuildTagsId(List<Object> recommendBuildTagsId) {
    this.recommendBuildTagsId = recommendBuildTagsId;
    return this;
  }

  public PlotEsfResponse addRecommendBuildTagsIdItem(Object recommendBuildTagsIdItem) {
    if (this.recommendBuildTagsId == null) {
      this.recommendBuildTagsId = new ArrayList<Object>();
    }
    this.recommendBuildTagsId.add(recommendBuildTagsIdItem);
    return this;
  }

  /**
   * Get recommendBuildTagsId
   * @return recommendBuildTagsId
  **/
  @ApiModelProperty(value = "")


  public List<Object> getRecommendBuildTagsId() {
    return recommendBuildTagsId;
  }

  public void setRecommendBuildTagsId(List<Object> recommendBuildTagsId) {
    this.recommendBuildTagsId = recommendBuildTagsId;
  }

  public PlotEsfResponse recommendBuildTagsName(List<Object> recommendBuildTagsName) {
    this.recommendBuildTagsName = recommendBuildTagsName;
    return this;
  }

  public PlotEsfResponse addRecommendBuildTagsNameItem(Object recommendBuildTagsNameItem) {
    if (this.recommendBuildTagsName == null) {
      this.recommendBuildTagsName = new ArrayList<Object>();
    }
    this.recommendBuildTagsName.add(recommendBuildTagsNameItem);
    return this;
  }

  /**
   * Get recommendBuildTagsName
   * @return recommendBuildTagsName
  **/
  @ApiModelProperty(value = "")


  public List<Object> getRecommendBuildTagsName() {
    return recommendBuildTagsName;
  }

  public void setRecommendBuildTagsName(List<Object> recommendBuildTagsName) {
    this.recommendBuildTagsName = recommendBuildTagsName;
  }

  public PlotEsfResponse room(Integer room) {
    this.room = room;
    return this;
  }

  /**
   * Get room
   * @return room
  **/
  @ApiModelProperty(value = "")


  public Integer getRoom() {
    return room;
  }

  public void setRoom(Integer room) {
    this.room = room;
  }

  public PlotEsfResponse tagsName(List<String> tagsName) {
    this.tagsName = tagsName;
    return this;
  }

  public PlotEsfResponse addTagsNameItem(String tagsNameItem) {
    if (this.tagsName == null) {
      this.tagsName = new ArrayList<String>();
    }
    this.tagsName.add(tagsNameItem);
    return this;
  }

  /**
   * Get tagsName
   * @return tagsName
  **/
  @ApiModelProperty(value = "")


  public List<String> getTagsName() {
    return tagsName;
  }

  public void setTagsName(List<String> tagsName) {
    this.tagsName = tagsName;
  }

  public PlotEsfResponse totalAbsoluteWithBizcircle(Double totalAbsoluteWithBizcircle) {
    this.totalAbsoluteWithBizcircle = totalAbsoluteWithBizcircle;
    return this;
  }

  /**
   * Get totalAbsoluteWithBizcircle
   * @return totalAbsoluteWithBizcircle
  **/
  @ApiModelProperty(value = "")


  public Double getTotalAbsoluteWithBizcircle() {
    return totalAbsoluteWithBizcircle;
  }

  public void setTotalAbsoluteWithBizcircle(Double totalAbsoluteWithBizcircle) {
    this.totalAbsoluteWithBizcircle = totalAbsoluteWithBizcircle;
  }

  public PlotEsfResponse totalAbsoluteWithCommunity(Double totalAbsoluteWithCommunity) {
    this.totalAbsoluteWithCommunity = totalAbsoluteWithCommunity;
    return this;
  }

  /**
   * Get totalAbsoluteWithCommunity
   * @return totalAbsoluteWithCommunity
  **/
  @ApiModelProperty(value = "")


  public Double getTotalAbsoluteWithCommunity() {
    return totalAbsoluteWithCommunity;
  }

  public void setTotalAbsoluteWithCommunity(Double totalAbsoluteWithCommunity) {
    this.totalAbsoluteWithCommunity = totalAbsoluteWithCommunity;
  }

  public PlotEsfResponse totalAbsoluteWithDistrict(Double totalAbsoluteWithDistrict) {
    this.totalAbsoluteWithDistrict = totalAbsoluteWithDistrict;
    return this;
  }

  /**
   * Get totalAbsoluteWithDistrict
   * @return totalAbsoluteWithDistrict
  **/
  @ApiModelProperty(value = "")


  public Double getTotalAbsoluteWithDistrict() {
    return totalAbsoluteWithDistrict;
  }

  public void setTotalAbsoluteWithDistrict(Double totalAbsoluteWithDistrict) {
    this.totalAbsoluteWithDistrict = totalAbsoluteWithDistrict;
  }

  public PlotEsfResponse totalRelativeWithBizcircle(Double totalRelativeWithBizcircle) {
    this.totalRelativeWithBizcircle = totalRelativeWithBizcircle;
    return this;
  }

  /**
   * Get totalRelativeWithBizcircle
   * @return totalRelativeWithBizcircle
  **/
  @ApiModelProperty(value = "")


  public Double getTotalRelativeWithBizcircle() {
    return totalRelativeWithBizcircle;
  }

  public void setTotalRelativeWithBizcircle(Double totalRelativeWithBizcircle) {
    this.totalRelativeWithBizcircle = totalRelativeWithBizcircle;
  }

  public PlotEsfResponse totalRelativeWithCommunity(Double totalRelativeWithCommunity) {
    this.totalRelativeWithCommunity = totalRelativeWithCommunity;
    return this;
  }

  /**
   * Get totalRelativeWithCommunity
   * @return totalRelativeWithCommunity
  **/
  @ApiModelProperty(value = "")


  public Double getTotalRelativeWithCommunity() {
    return totalRelativeWithCommunity;
  }

  public void setTotalRelativeWithCommunity(Double totalRelativeWithCommunity) {
    this.totalRelativeWithCommunity = totalRelativeWithCommunity;
  }

  public PlotEsfResponse totalRelativeWithDistrict(Double totalRelativeWithDistrict) {
    this.totalRelativeWithDistrict = totalRelativeWithDistrict;
    return this;
  }

  /**
   * Get totalRelativeWithDistrict
   * @return totalRelativeWithDistrict
  **/
  @ApiModelProperty(value = "")


  public Double getTotalRelativeWithDistrict() {
    return totalRelativeWithDistrict;
  }

  public void setTotalRelativeWithDistrict(Double totalRelativeWithDistrict) {
    this.totalRelativeWithDistrict = totalRelativeWithDistrict;
  }

  public PlotEsfResponse typeCounts(Map<String, Map<String, Integer>> typeCounts) {
    this.typeCounts = typeCounts;
    return this;
  }

  public PlotEsfResponse putTypeCountsItem(String key, Map<String, Integer> typeCountsItem) {
    if (this.typeCounts == null) {
      this.typeCounts = new HashMap<String, Map<String, Integer>>();
    }
    this.typeCounts.put(key, typeCountsItem);
    return this;
  }

  /**
   * Get typeCounts
   * @return typeCounts
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Map<String, Map<String, Integer>> getTypeCounts() {
    return typeCounts;
  }

  public void setTypeCounts(Map<String, Map<String, Integer>> typeCounts) {
    this.typeCounts = typeCounts;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlotEsfResponse plotEsfResponse = (PlotEsfResponse) o;
    return Objects.equals(this.agentBaseDo, plotEsfResponse.agentBaseDo) &&
        Objects.equals(this.area, plotEsfResponse.area) &&
        Objects.equals(this.areaId, plotEsfResponse.areaId) &&
        Objects.equals(this.avgAbsoluteWithBizcircle, plotEsfResponse.avgAbsoluteWithBizcircle) &&
        Objects.equals(this.avgAbsoluteWithCommunity, plotEsfResponse.avgAbsoluteWithCommunity) &&
        Objects.equals(this.avgAbsoluteWithDistrict, plotEsfResponse.avgAbsoluteWithDistrict) &&
        Objects.equals(this.avgDealCycle, plotEsfResponse.avgDealCycle) &&
        Objects.equals(this.avgRelativeWithBizcircle, plotEsfResponse.avgRelativeWithBizcircle) &&
        Objects.equals(this.avgRelativeWithCommunity, plotEsfResponse.avgRelativeWithCommunity) &&
        Objects.equals(this.avgRelativeWithDistrict, plotEsfResponse.avgRelativeWithDistrict) &&
        Objects.equals(this.buildArea, plotEsfResponse.buildArea) &&
        Objects.equals(this.forwardName, plotEsfResponse.forwardName) &&
        Objects.equals(this.hall, plotEsfResponse.hall) &&
        Objects.equals(this.houseBusinessName, plotEsfResponse.houseBusinessName) &&
        Objects.equals(this.houseBusinessNameId, plotEsfResponse.houseBusinessNameId) &&
        Objects.equals(this.houseId, plotEsfResponse.houseId) &&
        Objects.equals(this.housePhotoTitle, plotEsfResponse.housePhotoTitle) &&
        Objects.equals(this.houseTitle, plotEsfResponse.houseTitle) &&
        Objects.equals(this.houseTotalPrices, plotEsfResponse.houseTotalPrices) &&
        Objects.equals(this.houseUnitCost, plotEsfResponse.houseUnitCost) &&
        Objects.equals(this.importTime, plotEsfResponse.importTime) &&
        Objects.equals(this.isCommunityTopHouse, plotEsfResponse.isCommunityTopHouse) &&
        Objects.equals(this.isCutPrice, plotEsfResponse.isCutPrice) &&
        Objects.equals(this.isDealLayout, plotEsfResponse.isDealLayout) &&
        Objects.equals(this.isDefaultImage, plotEsfResponse.isDefaultImage) &&
        Objects.equals(this.isLowPrice, plotEsfResponse.isLowPrice) &&
        Objects.equals(this.isLowest, plotEsfResponse.isLowest) &&
        Objects.equals(this.isMainLayout, plotEsfResponse.isMainLayout) &&
        Objects.equals(this.isMustRob, plotEsfResponse.isMustRob) &&
        Objects.equals(this.isNew, plotEsfResponse.isNew) &&
        Objects.equals(this.nearPark, plotEsfResponse.nearPark) &&
        Objects.equals(this.newCode, plotEsfResponse.newCode) &&
        Objects.equals(this.plotName, plotEsfResponse.plotName) &&
        Objects.equals(this.priceFloat, plotEsfResponse.priceFloat) &&
        Objects.equals(this.rankInLowCommunityLayout, plotEsfResponse.rankInLowCommunityLayout) &&
        Objects.equals(this.rankLowInBizcircleLayout, plotEsfResponse.rankLowInBizcircleLayout) &&
        Objects.equals(this.recommendBuildTagsId, plotEsfResponse.recommendBuildTagsId) &&
        Objects.equals(this.recommendBuildTagsName, plotEsfResponse.recommendBuildTagsName) &&
        Objects.equals(this.room, plotEsfResponse.room) &&
        Objects.equals(this.tagsName, plotEsfResponse.tagsName) &&
        Objects.equals(this.totalAbsoluteWithBizcircle, plotEsfResponse.totalAbsoluteWithBizcircle) &&
        Objects.equals(this.totalAbsoluteWithCommunity, plotEsfResponse.totalAbsoluteWithCommunity) &&
        Objects.equals(this.totalAbsoluteWithDistrict, plotEsfResponse.totalAbsoluteWithDistrict) &&
        Objects.equals(this.totalRelativeWithBizcircle, plotEsfResponse.totalRelativeWithBizcircle) &&
        Objects.equals(this.totalRelativeWithCommunity, plotEsfResponse.totalRelativeWithCommunity) &&
        Objects.equals(this.totalRelativeWithDistrict, plotEsfResponse.totalRelativeWithDistrict) &&
        Objects.equals(this.typeCounts, plotEsfResponse.typeCounts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(agentBaseDo, area, areaId, avgAbsoluteWithBizcircle, avgAbsoluteWithCommunity, avgAbsoluteWithDistrict, avgDealCycle, avgRelativeWithBizcircle, avgRelativeWithCommunity, avgRelativeWithDistrict, buildArea, forwardName, hall, houseBusinessName, houseBusinessNameId, houseId, housePhotoTitle, houseTitle, houseTotalPrices, houseUnitCost, importTime, isCommunityTopHouse, isCutPrice, isDealLayout, isDefaultImage, isLowPrice, isLowest, isMainLayout, isMustRob, isNew, nearPark, newCode, plotName, priceFloat, rankInLowCommunityLayout, rankLowInBizcircleLayout, recommendBuildTagsId, recommendBuildTagsName, room, tagsName, totalAbsoluteWithBizcircle, totalAbsoluteWithCommunity, totalAbsoluteWithDistrict, totalRelativeWithBizcircle, totalRelativeWithCommunity, totalRelativeWithDistrict, typeCounts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlotEsfResponse {\n");
    
    sb.append("    agentBaseDo: ").append(toIndentedString(agentBaseDo)).append("\n");
    sb.append("    area: ").append(toIndentedString(area)).append("\n");
    sb.append("    areaId: ").append(toIndentedString(areaId)).append("\n");
    sb.append("    avgAbsoluteWithBizcircle: ").append(toIndentedString(avgAbsoluteWithBizcircle)).append("\n");
    sb.append("    avgAbsoluteWithCommunity: ").append(toIndentedString(avgAbsoluteWithCommunity)).append("\n");
    sb.append("    avgAbsoluteWithDistrict: ").append(toIndentedString(avgAbsoluteWithDistrict)).append("\n");
    sb.append("    avgDealCycle: ").append(toIndentedString(avgDealCycle)).append("\n");
    sb.append("    avgRelativeWithBizcircle: ").append(toIndentedString(avgRelativeWithBizcircle)).append("\n");
    sb.append("    avgRelativeWithCommunity: ").append(toIndentedString(avgRelativeWithCommunity)).append("\n");
    sb.append("    avgRelativeWithDistrict: ").append(toIndentedString(avgRelativeWithDistrict)).append("\n");
    sb.append("    buildArea: ").append(toIndentedString(buildArea)).append("\n");
    sb.append("    forwardName: ").append(toIndentedString(forwardName)).append("\n");
    sb.append("    hall: ").append(toIndentedString(hall)).append("\n");
    sb.append("    houseBusinessName: ").append(toIndentedString(houseBusinessName)).append("\n");
    sb.append("    houseBusinessNameId: ").append(toIndentedString(houseBusinessNameId)).append("\n");
    sb.append("    houseId: ").append(toIndentedString(houseId)).append("\n");
    sb.append("    housePhotoTitle: ").append(toIndentedString(housePhotoTitle)).append("\n");
    sb.append("    houseTitle: ").append(toIndentedString(houseTitle)).append("\n");
    sb.append("    houseTotalPrices: ").append(toIndentedString(houseTotalPrices)).append("\n");
    sb.append("    houseUnitCost: ").append(toIndentedString(houseUnitCost)).append("\n");
    sb.append("    importTime: ").append(toIndentedString(importTime)).append("\n");
    sb.append("    isCommunityTopHouse: ").append(toIndentedString(isCommunityTopHouse)).append("\n");
    sb.append("    isCutPrice: ").append(toIndentedString(isCutPrice)).append("\n");
    sb.append("    isDealLayout: ").append(toIndentedString(isDealLayout)).append("\n");
    sb.append("    isDefaultImage: ").append(toIndentedString(isDefaultImage)).append("\n");
    sb.append("    isLowPrice: ").append(toIndentedString(isLowPrice)).append("\n");
    sb.append("    isLowest: ").append(toIndentedString(isLowest)).append("\n");
    sb.append("    isMainLayout: ").append(toIndentedString(isMainLayout)).append("\n");
    sb.append("    isMustRob: ").append(toIndentedString(isMustRob)).append("\n");
    sb.append("    isNew: ").append(toIndentedString(isNew)).append("\n");
    sb.append("    nearPark: ").append(toIndentedString(nearPark)).append("\n");
    sb.append("    newCode: ").append(toIndentedString(newCode)).append("\n");
    sb.append("    plotName: ").append(toIndentedString(plotName)).append("\n");
    sb.append("    priceFloat: ").append(toIndentedString(priceFloat)).append("\n");
    sb.append("    rankInLowCommunityLayout: ").append(toIndentedString(rankInLowCommunityLayout)).append("\n");
    sb.append("    rankLowInBizcircleLayout: ").append(toIndentedString(rankLowInBizcircleLayout)).append("\n");
    sb.append("    recommendBuildTagsId: ").append(toIndentedString(recommendBuildTagsId)).append("\n");
    sb.append("    recommendBuildTagsName: ").append(toIndentedString(recommendBuildTagsName)).append("\n");
    sb.append("    room: ").append(toIndentedString(room)).append("\n");
    sb.append("    tagsName: ").append(toIndentedString(tagsName)).append("\n");
    sb.append("    totalAbsoluteWithBizcircle: ").append(toIndentedString(totalAbsoluteWithBizcircle)).append("\n");
    sb.append("    totalAbsoluteWithCommunity: ").append(toIndentedString(totalAbsoluteWithCommunity)).append("\n");
    sb.append("    totalAbsoluteWithDistrict: ").append(toIndentedString(totalAbsoluteWithDistrict)).append("\n");
    sb.append("    totalRelativeWithBizcircle: ").append(toIndentedString(totalRelativeWithBizcircle)).append("\n");
    sb.append("    totalRelativeWithCommunity: ").append(toIndentedString(totalRelativeWithCommunity)).append("\n");
    sb.append("    totalRelativeWithDistrict: ").append(toIndentedString(totalRelativeWithDistrict)).append("\n");
    sb.append("    typeCounts: ").append(toIndentedString(typeCounts)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

