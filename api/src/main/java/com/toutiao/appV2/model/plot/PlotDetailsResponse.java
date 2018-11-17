package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PlotDetailsResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class PlotDetailsResponse   {
  @JsonProperty("abbreviatedAge")
  private String abbreviatedAge = null;

  @JsonProperty("airQuality")
  private String airQuality = null;

  @JsonProperty("area")
  private String area = null;

  @JsonProperty("areaSize")
  private Double areaSize = null;

  @JsonProperty("avgGreening")
  private Double avgGreening = null;

  @JsonProperty("avgPrice")
  private Double avgPrice = null;

  @JsonProperty("buildingAreaSize")
  private Double buildingAreaSize = null;

  @JsonProperty("buildingStructure")
  private String buildingStructure = null;

  @JsonProperty("carPositionRatio")
  private String carPositionRatio = null;

  @JsonProperty("dimension")
  private Double dimension = null;

  @JsonProperty("electricFee")
  private Double electricFee = null;

  @JsonProperty("electricSupply")
  private String electricSupply = null;

  @JsonProperty("elevator")
  private String elevator = null;

  @JsonProperty("hasElevator")
  private String hasElevator = null;

  @JsonProperty("heatingMode")
  private String heatingMode = null;

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("label")
  @Valid
  private List<String> label = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("parkingRate")
  private String parkingRate = null;

  @JsonProperty("photos")
  @Valid
  private List<String> photos = null;

  @JsonProperty("plotsHousesDomain")
  private PlotsHousesDomain plotsHousesDomain = null;

  @JsonProperty("property")
  private String property = null;

  @JsonProperty("propertyFee")
  private String propertyFee = null;

  @JsonProperty("propertyType")
  private String propertyType = null;

  @JsonProperty("propertyTypeName")
  private String propertyTypeName = null;

  @JsonProperty("rc")
  private String rc = null;

  @JsonProperty("ringRoadName")
  private String ringRoadName = null;

  @JsonProperty("sumBuilding")
  private String sumBuilding = null;

  @JsonProperty("sumHousehold")
  private String sumHousehold = null;

  @JsonProperty("tradingArea")
  private String tradingArea = null;

  @JsonProperty("waterFee")
  private Double waterFee = null;

  @JsonProperty("waterSupply")
  private String waterSupply = null;

  public PlotDetailsResponse abbreviatedAge(String abbreviatedAge) {
    this.abbreviatedAge = abbreviatedAge;
    return this;
  }

  /**
   * Get abbreviatedAge
   * @return abbreviatedAge
  **/
  @ApiModelProperty(value = "")


  public String getAbbreviatedAge() {
    return abbreviatedAge;
  }

  public void setAbbreviatedAge(String abbreviatedAge) {
    this.abbreviatedAge = abbreviatedAge;
  }

  public PlotDetailsResponse airQuality(String airQuality) {
    this.airQuality = airQuality;
    return this;
  }

  /**
   * Get airQuality
   * @return airQuality
  **/
  @ApiModelProperty(value = "")


  public String getAirQuality() {
    return airQuality;
  }

  public void setAirQuality(String airQuality) {
    this.airQuality = airQuality;
  }

  public PlotDetailsResponse area(String area) {
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

  public PlotDetailsResponse areaSize(Double areaSize) {
    this.areaSize = areaSize;
    return this;
  }

  /**
   * Get areaSize
   * @return areaSize
  **/
  @ApiModelProperty(value = "")


  public Double getAreaSize() {
    return areaSize;
  }

  public void setAreaSize(Double areaSize) {
    this.areaSize = areaSize;
  }

  public PlotDetailsResponse avgGreening(Double avgGreening) {
    this.avgGreening = avgGreening;
    return this;
  }

  /**
   * Get avgGreening
   * @return avgGreening
  **/
  @ApiModelProperty(value = "")


  public Double getAvgGreening() {
    return avgGreening;
  }

  public void setAvgGreening(Double avgGreening) {
    this.avgGreening = avgGreening;
  }

  public PlotDetailsResponse avgPrice(Double avgPrice) {
    this.avgPrice = avgPrice;
    return this;
  }

  /**
   * Get avgPrice
   * @return avgPrice
  **/
  @ApiModelProperty(value = "")


  public Double getAvgPrice() {
    return avgPrice;
  }

  public void setAvgPrice(Double avgPrice) {
    this.avgPrice = avgPrice;
  }

  public PlotDetailsResponse buildingAreaSize(Double buildingAreaSize) {
    this.buildingAreaSize = buildingAreaSize;
    return this;
  }

  /**
   * Get buildingAreaSize
   * @return buildingAreaSize
  **/
  @ApiModelProperty(value = "")


  public Double getBuildingAreaSize() {
    return buildingAreaSize;
  }

  public void setBuildingAreaSize(Double buildingAreaSize) {
    this.buildingAreaSize = buildingAreaSize;
  }

  public PlotDetailsResponse buildingStructure(String buildingStructure) {
    this.buildingStructure = buildingStructure;
    return this;
  }

  /**
   * Get buildingStructure
   * @return buildingStructure
  **/
  @ApiModelProperty(value = "")


  public String getBuildingStructure() {
    return buildingStructure;
  }

  public void setBuildingStructure(String buildingStructure) {
    this.buildingStructure = buildingStructure;
  }

  public PlotDetailsResponse carPositionRatio(String carPositionRatio) {
    this.carPositionRatio = carPositionRatio;
    return this;
  }

  /**
   * Get carPositionRatio
   * @return carPositionRatio
  **/
  @ApiModelProperty(value = "")


  public String getCarPositionRatio() {
    return carPositionRatio;
  }

  public void setCarPositionRatio(String carPositionRatio) {
    this.carPositionRatio = carPositionRatio;
  }

  public PlotDetailsResponse dimension(Double dimension) {
    this.dimension = dimension;
    return this;
  }

  /**
   * Get dimension
   * @return dimension
  **/
  @ApiModelProperty(value = "")


  public Double getDimension() {
    return dimension;
  }

  public void setDimension(Double dimension) {
    this.dimension = dimension;
  }

  public PlotDetailsResponse electricFee(Double electricFee) {
    this.electricFee = electricFee;
    return this;
  }

  /**
   * Get electricFee
   * @return electricFee
  **/
  @ApiModelProperty(value = "")


  public Double getElectricFee() {
    return electricFee;
  }

  public void setElectricFee(Double electricFee) {
    this.electricFee = electricFee;
  }

  public PlotDetailsResponse electricSupply(String electricSupply) {
    this.electricSupply = electricSupply;
    return this;
  }

  /**
   * Get electricSupply
   * @return electricSupply
  **/
  @ApiModelProperty(value = "")


  public String getElectricSupply() {
    return electricSupply;
  }

  public void setElectricSupply(String electricSupply) {
    this.electricSupply = electricSupply;
  }

  public PlotDetailsResponse elevator(String elevator) {
    this.elevator = elevator;
    return this;
  }

  /**
   * Get elevator
   * @return elevator
  **/
  @ApiModelProperty(value = "")


  public String getElevator() {
    return elevator;
  }

  public void setElevator(String elevator) {
    this.elevator = elevator;
  }

  public PlotDetailsResponse hasElevator(String hasElevator) {
    this.hasElevator = hasElevator;
    return this;
  }

  /**
   * Get hasElevator
   * @return hasElevator
  **/
  @ApiModelProperty(value = "")


  public String getHasElevator() {
    return hasElevator;
  }

  public void setHasElevator(String hasElevator) {
    this.hasElevator = hasElevator;
  }

  public PlotDetailsResponse heatingMode(String heatingMode) {
    this.heatingMode = heatingMode;
    return this;
  }

  /**
   * Get heatingMode
   * @return heatingMode
  **/
  @ApiModelProperty(value = "")


  public String getHeatingMode() {
    return heatingMode;
  }

  public void setHeatingMode(String heatingMode) {
    this.heatingMode = heatingMode;
  }

  public PlotDetailsResponse id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PlotDetailsResponse label(List<String> label) {
    this.label = label;
    return this;
  }

  public PlotDetailsResponse addLabelItem(String labelItem) {
    if (this.label == null) {
      this.label = new ArrayList<String>();
    }
    this.label.add(labelItem);
    return this;
  }

  /**
   * Get label
   * @return label
  **/
  @ApiModelProperty(value = "")


  public List<String> getLabel() {
    return label;
  }

  public void setLabel(List<String> label) {
    this.label = label;
  }

  public PlotDetailsResponse location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(value = "")


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public PlotDetailsResponse parkingRate(String parkingRate) {
    this.parkingRate = parkingRate;
    return this;
  }

  /**
   * Get parkingRate
   * @return parkingRate
  **/
  @ApiModelProperty(value = "")


  public String getParkingRate() {
    return parkingRate;
  }

  public void setParkingRate(String parkingRate) {
    this.parkingRate = parkingRate;
  }

  public PlotDetailsResponse photos(List<String> photos) {
    this.photos = photos;
    return this;
  }

  public PlotDetailsResponse addPhotosItem(String photosItem) {
    if (this.photos == null) {
      this.photos = new ArrayList<String>();
    }
    this.photos.add(photosItem);
    return this;
  }

  /**
   * Get photos
   * @return photos
  **/
  @ApiModelProperty(value = "")


  public List<String> getPhotos() {
    return photos;
  }

  public void setPhotos(List<String> photos) {
    this.photos = photos;
  }

  public PlotDetailsResponse plotsHousesDomain(PlotsHousesDomain plotsHousesDomain) {
    this.plotsHousesDomain = plotsHousesDomain;
    return this;
  }

  /**
   * Get plotsHousesDomain
   * @return plotsHousesDomain
  **/
  @ApiModelProperty(value = "")

  @Valid

  public PlotsHousesDomain getPlotsHousesDomain() {
    return plotsHousesDomain;
  }

  public void setPlotsHousesDomain(PlotsHousesDomain plotsHousesDomain) {
    this.plotsHousesDomain = plotsHousesDomain;
  }

  public PlotDetailsResponse property(String property) {
    this.property = property;
    return this;
  }

  /**
   * Get property
   * @return property
  **/
  @ApiModelProperty(value = "")


  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public PlotDetailsResponse propertyFee(String propertyFee) {
    this.propertyFee = propertyFee;
    return this;
  }

  /**
   * Get propertyFee
   * @return propertyFee
  **/
  @ApiModelProperty(value = "")


  public String getPropertyFee() {
    return propertyFee;
  }

  public void setPropertyFee(String propertyFee) {
    this.propertyFee = propertyFee;
  }

  public PlotDetailsResponse propertyType(String propertyType) {
    this.propertyType = propertyType;
    return this;
  }

  /**
   * Get propertyType
   * @return propertyType
  **/
  @ApiModelProperty(value = "")


  public String getPropertyType() {
    return propertyType;
  }

  public void setPropertyType(String propertyType) {
    this.propertyType = propertyType;
  }

  public PlotDetailsResponse propertyTypeName(String propertyTypeName) {
    this.propertyTypeName = propertyTypeName;
    return this;
  }

  /**
   * Get propertyTypeName
   * @return propertyTypeName
  **/
  @ApiModelProperty(value = "")


  public String getPropertyTypeName() {
    return propertyTypeName;
  }

  public void setPropertyTypeName(String propertyTypeName) {
    this.propertyTypeName = propertyTypeName;
  }

  public PlotDetailsResponse rc(String rc) {
    this.rc = rc;
    return this;
  }

  /**
   * Get rc
   * @return rc
  **/
  @ApiModelProperty(value = "")


  public String getRc() {
    return rc;
  }

  public void setRc(String rc) {
    this.rc = rc;
  }

  public PlotDetailsResponse ringRoadName(String ringRoadName) {
    this.ringRoadName = ringRoadName;
    return this;
  }

  /**
   * Get ringRoadName
   * @return ringRoadName
  **/
  @ApiModelProperty(value = "")


  public String getRingRoadName() {
    return ringRoadName;
  }

  public void setRingRoadName(String ringRoadName) {
    this.ringRoadName = ringRoadName;
  }

  public PlotDetailsResponse sumBuilding(String sumBuilding) {
    this.sumBuilding = sumBuilding;
    return this;
  }

  /**
   * Get sumBuilding
   * @return sumBuilding
  **/
  @ApiModelProperty(value = "")


  public String getSumBuilding() {
    return sumBuilding;
  }

  public void setSumBuilding(String sumBuilding) {
    this.sumBuilding = sumBuilding;
  }

  public PlotDetailsResponse sumHousehold(String sumHousehold) {
    this.sumHousehold = sumHousehold;
    return this;
  }

  /**
   * Get sumHousehold
   * @return sumHousehold
  **/
  @ApiModelProperty(value = "")


  public String getSumHousehold() {
    return sumHousehold;
  }

  public void setSumHousehold(String sumHousehold) {
    this.sumHousehold = sumHousehold;
  }

  public PlotDetailsResponse tradingArea(String tradingArea) {
    this.tradingArea = tradingArea;
    return this;
  }

  /**
   * Get tradingArea
   * @return tradingArea
  **/
  @ApiModelProperty(value = "")


  public String getTradingArea() {
    return tradingArea;
  }

  public void setTradingArea(String tradingArea) {
    this.tradingArea = tradingArea;
  }

  public PlotDetailsResponse waterFee(Double waterFee) {
    this.waterFee = waterFee;
    return this;
  }

  /**
   * Get waterFee
   * @return waterFee
  **/
  @ApiModelProperty(value = "")


  public Double getWaterFee() {
    return waterFee;
  }

  public void setWaterFee(Double waterFee) {
    this.waterFee = waterFee;
  }

  public PlotDetailsResponse waterSupply(String waterSupply) {
    this.waterSupply = waterSupply;
    return this;
  }

  /**
   * Get waterSupply
   * @return waterSupply
  **/
  @ApiModelProperty(value = "")


  public String getWaterSupply() {
    return waterSupply;
  }

  public void setWaterSupply(String waterSupply) {
    this.waterSupply = waterSupply;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlotDetailsResponse plotDetailsResponse = (PlotDetailsResponse) o;
    return Objects.equals(this.abbreviatedAge, plotDetailsResponse.abbreviatedAge) &&
        Objects.equals(this.airQuality, plotDetailsResponse.airQuality) &&
        Objects.equals(this.area, plotDetailsResponse.area) &&
        Objects.equals(this.areaSize, plotDetailsResponse.areaSize) &&
        Objects.equals(this.avgGreening, plotDetailsResponse.avgGreening) &&
        Objects.equals(this.avgPrice, plotDetailsResponse.avgPrice) &&
        Objects.equals(this.buildingAreaSize, plotDetailsResponse.buildingAreaSize) &&
        Objects.equals(this.buildingStructure, plotDetailsResponse.buildingStructure) &&
        Objects.equals(this.carPositionRatio, plotDetailsResponse.carPositionRatio) &&
        Objects.equals(this.dimension, plotDetailsResponse.dimension) &&
        Objects.equals(this.electricFee, plotDetailsResponse.electricFee) &&
        Objects.equals(this.electricSupply, plotDetailsResponse.electricSupply) &&
        Objects.equals(this.elevator, plotDetailsResponse.elevator) &&
        Objects.equals(this.hasElevator, plotDetailsResponse.hasElevator) &&
        Objects.equals(this.heatingMode, plotDetailsResponse.heatingMode) &&
        Objects.equals(this.id, plotDetailsResponse.id) &&
        Objects.equals(this.label, plotDetailsResponse.label) &&
        Objects.equals(this.location, plotDetailsResponse.location) &&
        Objects.equals(this.parkingRate, plotDetailsResponse.parkingRate) &&
        Objects.equals(this.photos, plotDetailsResponse.photos) &&
        Objects.equals(this.plotsHousesDomain, plotDetailsResponse.plotsHousesDomain) &&
        Objects.equals(this.property, plotDetailsResponse.property) &&
        Objects.equals(this.propertyFee, plotDetailsResponse.propertyFee) &&
        Objects.equals(this.propertyType, plotDetailsResponse.propertyType) &&
        Objects.equals(this.propertyTypeName, plotDetailsResponse.propertyTypeName) &&
        Objects.equals(this.rc, plotDetailsResponse.rc) &&
        Objects.equals(this.ringRoadName, plotDetailsResponse.ringRoadName) &&
        Objects.equals(this.sumBuilding, plotDetailsResponse.sumBuilding) &&
        Objects.equals(this.sumHousehold, plotDetailsResponse.sumHousehold) &&
        Objects.equals(this.tradingArea, plotDetailsResponse.tradingArea) &&
        Objects.equals(this.waterFee, plotDetailsResponse.waterFee) &&
        Objects.equals(this.waterSupply, plotDetailsResponse.waterSupply);
  }

  @Override
  public int hashCode() {
    return Objects.hash(abbreviatedAge, airQuality, area, areaSize, avgGreening, avgPrice, buildingAreaSize, buildingStructure, carPositionRatio, dimension, electricFee, electricSupply, elevator, hasElevator, heatingMode, id, label, location, parkingRate, photos, plotsHousesDomain, property, propertyFee, propertyType, propertyTypeName, rc, ringRoadName, sumBuilding, sumHousehold, tradingArea, waterFee, waterSupply);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlotDetailsResponse {\n");
    
    sb.append("    abbreviatedAge: ").append(toIndentedString(abbreviatedAge)).append("\n");
    sb.append("    airQuality: ").append(toIndentedString(airQuality)).append("\n");
    sb.append("    area: ").append(toIndentedString(area)).append("\n");
    sb.append("    areaSize: ").append(toIndentedString(areaSize)).append("\n");
    sb.append("    avgGreening: ").append(toIndentedString(avgGreening)).append("\n");
    sb.append("    avgPrice: ").append(toIndentedString(avgPrice)).append("\n");
    sb.append("    buildingAreaSize: ").append(toIndentedString(buildingAreaSize)).append("\n");
    sb.append("    buildingStructure: ").append(toIndentedString(buildingStructure)).append("\n");
    sb.append("    carPositionRatio: ").append(toIndentedString(carPositionRatio)).append("\n");
    sb.append("    dimension: ").append(toIndentedString(dimension)).append("\n");
    sb.append("    electricFee: ").append(toIndentedString(electricFee)).append("\n");
    sb.append("    electricSupply: ").append(toIndentedString(electricSupply)).append("\n");
    sb.append("    elevator: ").append(toIndentedString(elevator)).append("\n");
    sb.append("    hasElevator: ").append(toIndentedString(hasElevator)).append("\n");
    sb.append("    heatingMode: ").append(toIndentedString(heatingMode)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    parkingRate: ").append(toIndentedString(parkingRate)).append("\n");
    sb.append("    photos: ").append(toIndentedString(photos)).append("\n");
    sb.append("    plotsHousesDomain: ").append(toIndentedString(plotsHousesDomain)).append("\n");
    sb.append("    property: ").append(toIndentedString(property)).append("\n");
    sb.append("    propertyFee: ").append(toIndentedString(propertyFee)).append("\n");
    sb.append("    propertyType: ").append(toIndentedString(propertyType)).append("\n");
    sb.append("    propertyTypeName: ").append(toIndentedString(propertyTypeName)).append("\n");
    sb.append("    rc: ").append(toIndentedString(rc)).append("\n");
    sb.append("    ringRoadName: ").append(toIndentedString(ringRoadName)).append("\n");
    sb.append("    sumBuilding: ").append(toIndentedString(sumBuilding)).append("\n");
    sb.append("    sumHousehold: ").append(toIndentedString(sumHousehold)).append("\n");
    sb.append("    tradingArea: ").append(toIndentedString(tradingArea)).append("\n");
    sb.append("    waterFee: ").append(toIndentedString(waterFee)).append("\n");
    sb.append("    waterSupply: ").append(toIndentedString(waterSupply)).append("\n");
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

