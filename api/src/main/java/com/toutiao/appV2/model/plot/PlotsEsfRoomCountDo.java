package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * PlotsEsfRoomCountDo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class PlotsEsfRoomCountDo   {
  @JsonProperty("count")
  private Long count = null;

  @JsonProperty("room")
  private Object room = null;

  public PlotsEsfRoomCountDo count(Long count) {
    this.count = count;
    return this;
  }

  /**
   * Get count
   * @return count
  **/
  @ApiModelProperty(value = "")


  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public PlotsEsfRoomCountDo room(Object room) {
    this.room = room;
    return this;
  }

  /**
   * Get room
   * @return room
  **/
  @ApiModelProperty(value = "")


  public Object getRoom() {
    return room;
  }

  public void setRoom(Object room) {
    this.room = room;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlotsEsfRoomCountDo plotsEsfRoomCountDo = (PlotsEsfRoomCountDo) o;
    return Objects.equals(this.count, plotsEsfRoomCountDo.count) &&
        Objects.equals(this.room, plotsEsfRoomCountDo.room);
  }

  @Override
  public int hashCode() {
    return Objects.hash(count, room);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlotsEsfRoomCountDo {\n");
    
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    room: ").append(toIndentedString(room)).append("\n");
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

