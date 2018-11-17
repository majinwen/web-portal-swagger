package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PlotsEsfRoomCountResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class PlotsEsfRoomCountResponse   {
  @JsonProperty("rooms")
  @Valid
  private List<PlotsEsfRoomCountDo> rooms = null;

  @JsonProperty("totalCount")
  private Long totalCount = null;

  public PlotsEsfRoomCountResponse rooms(List<PlotsEsfRoomCountDo> rooms) {
    this.rooms = rooms;
    return this;
  }

  public PlotsEsfRoomCountResponse addRoomsItem(PlotsEsfRoomCountDo roomsItem) {
    if (this.rooms == null) {
      this.rooms = new ArrayList<PlotsEsfRoomCountDo>();
    }
    this.rooms.add(roomsItem);
    return this;
  }

  /**
   * Get rooms
   * @return rooms
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<PlotsEsfRoomCountDo> getRooms() {
    return rooms;
  }

  public void setRooms(List<PlotsEsfRoomCountDo> rooms) {
    this.rooms = rooms;
  }

  public PlotsEsfRoomCountResponse totalCount(Long totalCount) {
    this.totalCount = totalCount;
    return this;
  }

  /**
   * Get totalCount
   * @return totalCount
  **/
  @ApiModelProperty(value = "")


  public Long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Long totalCount) {
    this.totalCount = totalCount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlotsEsfRoomCountResponse plotsEsfRoomCountResponse = (PlotsEsfRoomCountResponse) o;
    return Objects.equals(this.rooms, plotsEsfRoomCountResponse.rooms) &&
        Objects.equals(this.totalCount, plotsEsfRoomCountResponse.totalCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rooms, totalCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlotsEsfRoomCountResponse {\n");
    
    sb.append("    rooms: ").append(toIndentedString(rooms)).append("\n");
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
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

