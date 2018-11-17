package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PlotDetailsFewResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class PlotDetailsFewResponse   {
  @JsonProperty("abbreviatedAge")
  private String abbreviatedAge = null;

  @JsonProperty("address")
  private String address = null;

  @JsonProperty("alias")
  private String alias = null;

  @JsonProperty("area")
  private String area = null;

  @JsonProperty("areaId")
  private String areaId = null;

  @JsonProperty("avgPrice")
  private Integer avgPrice = null;

  @JsonProperty("carPositionRatio")
  private String carPositionRatio = null;

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("key")
  private String key = null;

  @JsonProperty("label")
  @Valid
  private List<String> label = null;

  @JsonProperty("labelId")
  @Valid
  private List<String> labelId = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("metroStation")
  @Valid
  private List<String> metroStation = null;

  @JsonProperty("metroStationId")
  @Valid
  private List<String> metroStationId = null;

  @JsonProperty("metroWithPlotsDistance")
  private Object metroWithPlotsDistance = null;

  @JsonProperty("photo")
  @Valid
  private List<String> photo = null;

  @JsonProperty("rc")
  private String rc = null;

  @JsonProperty("subwayLine")
  @Valid
  private List<String> subwayLine = null;

  @JsonProperty("subwayLineId")
  @Valid
  private List<String> subwayLineId = null;

  @JsonProperty("sumPrice")
  private Integer sumPrice = null;

  @JsonProperty("tradingArea")
  private String tradingArea = null;

  @JsonProperty("tradingAreaId")
  private String tradingAreaId = null;

  @JsonProperty("trafficInformation")
  private String trafficInformation = null;

  public PlotDetailsFewResponse abbreviatedAge(String abbreviatedAge) {
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

  public PlotDetailsFewResponse address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(value = "")


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public PlotDetailsFewResponse alias(String alias) {
    this.alias = alias;
    return this;
  }

  /**
   * Get alias
   * @return alias
  **/
  @ApiModelProperty(value = "")


  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public PlotDetailsFewResponse area(String area) {
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

  public PlotDetailsFewResponse areaId(String areaId) {
    this.areaId = areaId;
    return this;
  }

  /**
   * Get areaId
   * @return areaId
  **/
  @ApiModelProperty(value = "")


  public String getAreaId() {
    return areaId;
  }

  public void setAreaId(String areaId) {
    this.areaId = areaId;
  }

  public PlotDetailsFewResponse avgPrice(Integer avgPrice) {
    this.avgPrice = avgPrice;
    return this;
  }

  /**
   * Get avgPrice
   * @return avgPrice
  **/
  @ApiModelProperty(value = "")


  public Integer getAvgPrice() {
    return avgPrice;
  }

  public void setAvgPrice(Integer avgPrice) {
    this.avgPrice = avgPrice;
  }

  public PlotDetailsFewResponse carPositionRatio(String carPositionRatio) {
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

  public PlotDetailsFewResponse id(Integer id) {
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

  public PlotDetailsFewResponse key(String key) {
    this.key = key;
    return this;
  }

  /**
   * Get key
   * @return key
  **/
  @ApiModelProperty(value = "")


  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public PlotDetailsFewResponse label(List<String> label) {
    this.label = label;
    return this;
  }

  public PlotDetailsFewResponse addLabelItem(String labelItem) {
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

  public PlotDetailsFewResponse labelId(List<String> labelId) {
    this.labelId = labelId;
    return this;
  }

  public PlotDetailsFewResponse addLabelIdItem(String labelIdItem) {
    if (this.labelId == null) {
      this.labelId = new ArrayList<String>();
    }
    this.labelId.add(labelIdItem);
    return this;
  }

  /**
   * Get labelId
   * @return labelId
  **/
  @ApiModelProperty(value = "")


  public List<String> getLabelId() {
    return labelId;
  }

  public void setLabelId(List<String> labelId) {
    this.labelId = labelId;
  }

  public PlotDetailsFewResponse location(String location) {
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

  public PlotDetailsFewResponse metroStation(List<String> metroStation) {
    this.metroStation = metroStation;
    return this;
  }

  public PlotDetailsFewResponse addMetroStationItem(String metroStationItem) {
    if (this.metroStation == null) {
      this.metroStation = new ArrayList<String>();
    }
    this.metroStation.add(metroStationItem);
    return this;
  }

  /**
   * Get metroStation
   * @return metroStation
  **/
  @ApiModelProperty(value = "")


  public List<String> getMetroStation() {
    return metroStation;
  }

  public void setMetroStation(List<String> metroStation) {
    this.metroStation = metroStation;
  }

  public PlotDetailsFewResponse metroStationId(List<String> metroStationId) {
    this.metroStationId = metroStationId;
    return this;
  }

  public PlotDetailsFewResponse addMetroStationIdItem(String metroStationIdItem) {
    if (this.metroStationId == null) {
      this.metroStationId = new ArrayList<String>();
    }
    this.metroStationId.add(metroStationIdItem);
    return this;
  }

  /**
   * Get metroStationId
   * @return metroStationId
  **/
  @ApiModelProperty(value = "")


  public List<String> getMetroStationId() {
    return metroStationId;
  }

  public void setMetroStationId(List<String> metroStationId) {
    this.metroStationId = metroStationId;
  }

  public PlotDetailsFewResponse metroWithPlotsDistance(Object metroWithPlotsDistance) {
    this.metroWithPlotsDistance = metroWithPlotsDistance;
    return this;
  }

  /**
   * Get metroWithPlotsDistance
   * @return metroWithPlotsDistance
  **/
  @ApiModelProperty(value = "")


  public Object getMetroWithPlotsDistance() {
    return metroWithPlotsDistance;
  }

  public void setMetroWithPlotsDistance(Object metroWithPlotsDistance) {
    this.metroWithPlotsDistance = metroWithPlotsDistance;
  }

  public PlotDetailsFewResponse photo(List<String> photo) {
    this.photo = photo;
    return this;
  }

  public PlotDetailsFewResponse addPhotoItem(String photoItem) {
    if (this.photo == null) {
      this.photo = new ArrayList<String>();
    }
    this.photo.add(photoItem);
    return this;
  }

  /**
   * Get photo
   * @return photo
  **/
  @ApiModelProperty(value = "")


  public List<String> getPhoto() {
    return photo;
  }

  public void setPhoto(List<String> photo) {
    this.photo = photo;
  }

  public PlotDetailsFewResponse rc(String rc) {
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

  public PlotDetailsFewResponse subwayLine(List<String> subwayLine) {
    this.subwayLine = subwayLine;
    return this;
  }

  public PlotDetailsFewResponse addSubwayLineItem(String subwayLineItem) {
    if (this.subwayLine == null) {
      this.subwayLine = new ArrayList<String>();
    }
    this.subwayLine.add(subwayLineItem);
    return this;
  }

  /**
   * Get subwayLine
   * @return subwayLine
  **/
  @ApiModelProperty(value = "")


  public List<String> getSubwayLine() {
    return subwayLine;
  }

  public void setSubwayLine(List<String> subwayLine) {
    this.subwayLine = subwayLine;
  }

  public PlotDetailsFewResponse subwayLineId(List<String> subwayLineId) {
    this.subwayLineId = subwayLineId;
    return this;
  }

  public PlotDetailsFewResponse addSubwayLineIdItem(String subwayLineIdItem) {
    if (this.subwayLineId == null) {
      this.subwayLineId = new ArrayList<String>();
    }
    this.subwayLineId.add(subwayLineIdItem);
    return this;
  }

  /**
   * Get subwayLineId
   * @return subwayLineId
  **/
  @ApiModelProperty(value = "")


  public List<String> getSubwayLineId() {
    return subwayLineId;
  }

  public void setSubwayLineId(List<String> subwayLineId) {
    this.subwayLineId = subwayLineId;
  }

  public PlotDetailsFewResponse sumPrice(Integer sumPrice) {
    this.sumPrice = sumPrice;
    return this;
  }

  /**
   * Get sumPrice
   * @return sumPrice
  **/
  @ApiModelProperty(value = "")


  public Integer getSumPrice() {
    return sumPrice;
  }

  public void setSumPrice(Integer sumPrice) {
    this.sumPrice = sumPrice;
  }

  public PlotDetailsFewResponse tradingArea(String tradingArea) {
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

  public PlotDetailsFewResponse tradingAreaId(String tradingAreaId) {
    this.tradingAreaId = tradingAreaId;
    return this;
  }

  /**
   * Get tradingAreaId
   * @return tradingAreaId
  **/
  @ApiModelProperty(value = "")


  public String getTradingAreaId() {
    return tradingAreaId;
  }

  public void setTradingAreaId(String tradingAreaId) {
    this.tradingAreaId = tradingAreaId;
  }

  public PlotDetailsFewResponse trafficInformation(String trafficInformation) {
    this.trafficInformation = trafficInformation;
    return this;
  }

  /**
   * Get trafficInformation
   * @return trafficInformation
  **/
  @ApiModelProperty(value = "")


  public String getTrafficInformation() {
    return trafficInformation;
  }

  public void setTrafficInformation(String trafficInformation) {
    this.trafficInformation = trafficInformation;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlotDetailsFewResponse plotDetailsFewResponse = (PlotDetailsFewResponse) o;
    return Objects.equals(this.abbreviatedAge, plotDetailsFewResponse.abbreviatedAge) &&
        Objects.equals(this.address, plotDetailsFewResponse.address) &&
        Objects.equals(this.alias, plotDetailsFewResponse.alias) &&
        Objects.equals(this.area, plotDetailsFewResponse.area) &&
        Objects.equals(this.areaId, plotDetailsFewResponse.areaId) &&
        Objects.equals(this.avgPrice, plotDetailsFewResponse.avgPrice) &&
        Objects.equals(this.carPositionRatio, plotDetailsFewResponse.carPositionRatio) &&
        Objects.equals(this.id, plotDetailsFewResponse.id) &&
        Objects.equals(this.key, plotDetailsFewResponse.key) &&
        Objects.equals(this.label, plotDetailsFewResponse.label) &&
        Objects.equals(this.labelId, plotDetailsFewResponse.labelId) &&
        Objects.equals(this.location, plotDetailsFewResponse.location) &&
        Objects.equals(this.metroStation, plotDetailsFewResponse.metroStation) &&
        Objects.equals(this.metroStationId, plotDetailsFewResponse.metroStationId) &&
        Objects.equals(this.metroWithPlotsDistance, plotDetailsFewResponse.metroWithPlotsDistance) &&
        Objects.equals(this.photo, plotDetailsFewResponse.photo) &&
        Objects.equals(this.rc, plotDetailsFewResponse.rc) &&
        Objects.equals(this.subwayLine, plotDetailsFewResponse.subwayLine) &&
        Objects.equals(this.subwayLineId, plotDetailsFewResponse.subwayLineId) &&
        Objects.equals(this.sumPrice, plotDetailsFewResponse.sumPrice) &&
        Objects.equals(this.tradingArea, plotDetailsFewResponse.tradingArea) &&
        Objects.equals(this.tradingAreaId, plotDetailsFewResponse.tradingAreaId) &&
        Objects.equals(this.trafficInformation, plotDetailsFewResponse.trafficInformation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(abbreviatedAge, address, alias, area, areaId, avgPrice, carPositionRatio, id, key, label, labelId, location, metroStation, metroStationId, metroWithPlotsDistance, photo, rc, subwayLine, subwayLineId, sumPrice, tradingArea, tradingAreaId, trafficInformation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlotDetailsFewResponse {\n");
    
    sb.append("    abbreviatedAge: ").append(toIndentedString(abbreviatedAge)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    alias: ").append(toIndentedString(alias)).append("\n");
    sb.append("    area: ").append(toIndentedString(area)).append("\n");
    sb.append("    areaId: ").append(toIndentedString(areaId)).append("\n");
    sb.append("    avgPrice: ").append(toIndentedString(avgPrice)).append("\n");
    sb.append("    carPositionRatio: ").append(toIndentedString(carPositionRatio)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    labelId: ").append(toIndentedString(labelId)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    metroStation: ").append(toIndentedString(metroStation)).append("\n");
    sb.append("    metroStationId: ").append(toIndentedString(metroStationId)).append("\n");
    sb.append("    metroWithPlotsDistance: ").append(toIndentedString(metroWithPlotsDistance)).append("\n");
    sb.append("    photo: ").append(toIndentedString(photo)).append("\n");
    sb.append("    rc: ").append(toIndentedString(rc)).append("\n");
    sb.append("    subwayLine: ").append(toIndentedString(subwayLine)).append("\n");
    sb.append("    subwayLineId: ").append(toIndentedString(subwayLineId)).append("\n");
    sb.append("    sumPrice: ").append(toIndentedString(sumPrice)).append("\n");
    sb.append("    tradingArea: ").append(toIndentedString(tradingArea)).append("\n");
    sb.append("    tradingAreaId: ").append(toIndentedString(tradingAreaId)).append("\n");
    sb.append("    trafficInformation: ").append(toIndentedString(trafficInformation)).append("\n");
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

