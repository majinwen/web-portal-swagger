package com.toutiao.appV2.model.favorite;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * AddFavorite
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T08:21:25.634Z")

public class AddFavorite {
    @JsonProperty("buildArea")
    private Double buildArea = null;

    @JsonProperty("buildingName")
    private String buildingName = null;

    @JsonProperty("forward")
    private String forward = null;

    @JsonProperty("houseArea")
    private Double houseArea = null;

    @JsonProperty("houseId")
    private String houseId = null;

    @JsonProperty("housePhotoTitle")
    private String housePhotoTitle = null;

    @JsonProperty("houseTitle")
    private String houseTitle = null;

    @JsonProperty("houseTotalPrices")
    private Double houseTotalPrices = null;

    @JsonProperty("isClaim")
    private Integer isClaim = null;

    @JsonProperty("isCutPrice")
    private String isCutPrice = null;

    @JsonProperty("rentPrice")
    private Double rentPrice = null;

    @JsonProperty("rentType")
    private String rentType = null;

    @JsonProperty("room")
    private Integer room = null;

    @JsonProperty("userId")
    private Integer userId = null;

    public AddFavorite buildArea(Double buildArea) {
        this.buildArea = buildArea;
        return this;
    }

    /**
     * Get buildArea
     *
     * @return buildArea
     **/
    @ApiModelProperty(value = "")


    public Double getBuildArea() {
        return buildArea;
    }

    public void setBuildArea(Double buildArea) {
        this.buildArea = buildArea;
    }

    public AddFavorite buildingName(String buildingName) {
        this.buildingName = buildingName;
        return this;
    }

    /**
     * Get buildingName
     *
     * @return buildingName
     **/
    @ApiModelProperty(value = "")


    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public AddFavorite forward(String forward) {
        this.forward = forward;
        return this;
    }

    /**
     * Get forward
     *
     * @return forward
     **/
    @ApiModelProperty(value = "")


    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public AddFavorite houseArea(Double houseArea) {
        this.houseArea = houseArea;
        return this;
    }

    /**
     * Get houseArea
     *
     * @return houseArea
     **/
    @ApiModelProperty(value = "")


    public Double getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(Double houseArea) {
        this.houseArea = houseArea;
    }

    public AddFavorite houseId(String houseId) {
        this.houseId = houseId;
        return this;
    }

    /**
     * Get houseId
     *
     * @return houseId
     **/
    @ApiModelProperty(value = "")


    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public AddFavorite housePhotoTitle(String housePhotoTitle) {
        this.housePhotoTitle = housePhotoTitle;
        return this;
    }

    /**
     * Get housePhotoTitle
     *
     * @return housePhotoTitle
     **/
    @ApiModelProperty(value = "")


    public String getHousePhotoTitle() {
        return housePhotoTitle;
    }

    public void setHousePhotoTitle(String housePhotoTitle) {
        this.housePhotoTitle = housePhotoTitle;
    }

    public AddFavorite houseTitle(String houseTitle) {
        this.houseTitle = houseTitle;
        return this;
    }

    /**
     * Get houseTitle
     *
     * @return houseTitle
     **/
    @ApiModelProperty(value = "")


    public String getHouseTitle() {
        return houseTitle;
    }

    public void setHouseTitle(String houseTitle) {
        this.houseTitle = houseTitle;
    }

    public AddFavorite houseTotalPrices(Double houseTotalPrices) {
        this.houseTotalPrices = houseTotalPrices;
        return this;
    }

    /**
     * Get houseTotalPrices
     *
     * @return houseTotalPrices
     **/
    @ApiModelProperty(value = "")


    public Double getHouseTotalPrices() {
        return houseTotalPrices;
    }

    public void setHouseTotalPrices(Double houseTotalPrices) {
        this.houseTotalPrices = houseTotalPrices;
    }

    public AddFavorite isClaim(Integer isClaim) {
        this.isClaim = isClaim;
        return this;
    }

    /**
     * Get isClaim
     *
     * @return isClaim
     **/
    @ApiModelProperty(value = "")


    public Integer getIsClaim() {
        return isClaim;
    }

    public void setIsClaim(Integer isClaim) {
        this.isClaim = isClaim;
    }

    public AddFavorite isCutPrice(String isCutPrice) {
        this.isCutPrice = isCutPrice;
        return this;
    }

    /**
     * Get isCutPrice
     *
     * @return isCutPrice
     **/
    @ApiModelProperty(value = "")


    public String getIsCutPrice() {
        return isCutPrice;
    }

    public void setIsCutPrice(String isCutPrice) {
        this.isCutPrice = isCutPrice;
    }

    public AddFavorite rentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
        return this;
    }

    /**
     * Get rentPrice
     *
     * @return rentPrice
     **/
    @ApiModelProperty(value = "")


    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public AddFavorite rentType(String rentType) {
        this.rentType = rentType;
        return this;
    }

    /**
     * Get rentType
     *
     * @return rentType
     **/
    @ApiModelProperty(value = "")


    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public AddFavorite room(Integer room) {
        this.room = room;
        return this;
    }

    /**
     * Get room
     *
     * @return room
     **/
    @ApiModelProperty(value = "")


    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public AddFavorite userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Get userId
     *
     * @return userId
     **/
    @ApiModelProperty(value = "")


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddFavorite addFavorite = (AddFavorite) o;
        return Objects.equals(this.buildArea, addFavorite.buildArea) &&
                Objects.equals(this.buildingName, addFavorite.buildingName) &&
                Objects.equals(this.forward, addFavorite.forward) &&
                Objects.equals(this.houseArea, addFavorite.houseArea) &&
                Objects.equals(this.houseId, addFavorite.houseId) &&
                Objects.equals(this.housePhotoTitle, addFavorite.housePhotoTitle) &&
                Objects.equals(this.houseTitle, addFavorite.houseTitle) &&
                Objects.equals(this.houseTotalPrices, addFavorite.houseTotalPrices) &&
                Objects.equals(this.isClaim, addFavorite.isClaim) &&
                Objects.equals(this.isCutPrice, addFavorite.isCutPrice) &&
                Objects.equals(this.rentPrice, addFavorite.rentPrice) &&
                Objects.equals(this.rentType, addFavorite.rentType) &&
                Objects.equals(this.room, addFavorite.room) &&
                Objects.equals(this.userId, addFavorite.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildArea, buildingName, forward, houseArea, houseId, housePhotoTitle, houseTitle, houseTotalPrices, isClaim, isCutPrice, rentPrice, rentType, room, userId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AddFavorite {\n");

        sb.append("    buildArea: ").append(toIndentedString(buildArea)).append("\n");
        sb.append("    buildingName: ").append(toIndentedString(buildingName)).append("\n");
        sb.append("    forward: ").append(toIndentedString(forward)).append("\n");
        sb.append("    houseArea: ").append(toIndentedString(houseArea)).append("\n");
        sb.append("    houseId: ").append(toIndentedString(houseId)).append("\n");
        sb.append("    housePhotoTitle: ").append(toIndentedString(housePhotoTitle)).append("\n");
        sb.append("    houseTitle: ").append(toIndentedString(houseTitle)).append("\n");
        sb.append("    houseTotalPrices: ").append(toIndentedString(houseTotalPrices)).append("\n");
        sb.append("    isClaim: ").append(toIndentedString(isClaim)).append("\n");
        sb.append("    isCutPrice: ").append(toIndentedString(isCutPrice)).append("\n");
        sb.append("    rentPrice: ").append(toIndentedString(rentPrice)).append("\n");
        sb.append("    rentType: ").append(toIndentedString(rentType)).append("\n");
        sb.append("    room: ").append(toIndentedString(room)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

