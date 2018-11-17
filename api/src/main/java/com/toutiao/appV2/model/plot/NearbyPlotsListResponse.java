package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * NearbyPlotsListResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class NearbyPlotsListResponse   {
  @JsonProperty("nearbyPlots")
  @Valid
  private List<PlotDetailsFewDo> nearbyPlots = null;

  @JsonProperty("totals")
  private Long totals = null;

  public NearbyPlotsListResponse nearbyPlots(List<PlotDetailsFewDo> nearbyPlots) {
    this.nearbyPlots = nearbyPlots;
    return this;
  }

  public NearbyPlotsListResponse addNearbyPlotsItem(PlotDetailsFewDo nearbyPlotsItem) {
    if (this.nearbyPlots == null) {
      this.nearbyPlots = new ArrayList<PlotDetailsFewDo>();
    }
    this.nearbyPlots.add(nearbyPlotsItem);
    return this;
  }

  /**
   * Get nearbyPlots
   * @return nearbyPlots
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<PlotDetailsFewDo> getNearbyPlots() {
    return nearbyPlots;
  }

  public void setNearbyPlots(List<PlotDetailsFewDo> nearbyPlots) {
    this.nearbyPlots = nearbyPlots;
  }

  public NearbyPlotsListResponse totals(Long totals) {
    this.totals = totals;
    return this;
  }

  /**
   * Get totals
   * @return totals
  **/
  @ApiModelProperty(value = "")


  public Long getTotals() {
    return totals;
  }

  public void setTotals(Long totals) {
    this.totals = totals;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NearbyPlotsListResponse nearbyPlotsListResponse = (NearbyPlotsListResponse) o;
    return Objects.equals(this.nearbyPlots, nearbyPlotsListResponse.nearbyPlots) &&
        Objects.equals(this.totals, nearbyPlotsListResponse.totals);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nearbyPlots, totals);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NearbyPlotsListResponse {\n");
    
    sb.append("    nearbyPlots: ").append(toIndentedString(nearbyPlots)).append("\n");
    sb.append("    totals: ").append(toIndentedString(totals)).append("\n");
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

