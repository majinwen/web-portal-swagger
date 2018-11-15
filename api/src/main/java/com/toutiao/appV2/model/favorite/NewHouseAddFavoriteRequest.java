package com.toutiao.appV2.model.favorite;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * NewHouseAddFavoriteRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T08:21:25.634Z")

public class NewHouseAddFavoriteRequest {
    @JsonProperty("averagePrice")
    private BigDecimal averagePrice = null;

    @JsonProperty("buildingId")
    private Integer buildingId = null;

    @JsonProperty("buildingName")
    private String buildingName = null;

    @JsonProperty("buildingTitleImg")
    private String buildingTitleImg = null;

    @JsonProperty("houseMaxArea")
    private String houseMaxArea = null;

    @JsonProperty("houseMinArea")
    private String houseMinArea = null;

    @JsonProperty("isDel")
    private Integer isDel = null;

    @JsonProperty("status")
    private Integer status = null;

    @JsonProperty("totalPrice")
    private BigDecimal totalPrice = null;

    @JsonProperty("userId")
    private Integer userId = null;

    public NewHouseAddFavoriteRequest averagePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
        return this;
    }

    /**
     * Get averagePrice
     *
     * @return averagePrice
     **/
    @ApiModelProperty(value = "")

    @Valid

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public NewHouseAddFavoriteRequest buildingId(Integer buildingId) {
        this.buildingId = buildingId;
        return this;
    }

    /**
     * Get buildingId
     *
     * @return buildingId
     **/
    @ApiModelProperty(value = "")


    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public NewHouseAddFavoriteRequest buildingName(String buildingName) {
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

    public NewHouseAddFavoriteRequest buildingTitleImg(String buildingTitleImg) {
        this.buildingTitleImg = buildingTitleImg;
        return this;
    }

    /**
     * Get buildingTitleImg
     *
     * @return buildingTitleImg
     **/
    @ApiModelProperty(value = "")


    public String getBuildingTitleImg() {
        return buildingTitleImg;
    }

    public void setBuildingTitleImg(String buildingTitleImg) {
        this.buildingTitleImg = buildingTitleImg;
    }

    public NewHouseAddFavoriteRequest houseMaxArea(String houseMaxArea) {
        this.houseMaxArea = houseMaxArea;
        return this;
    }

    /**
     * Get houseMaxArea
     *
     * @return houseMaxArea
     **/
    @ApiModelProperty(value = "")


    public String getHouseMaxArea() {
        return houseMaxArea;
    }

    public void setHouseMaxArea(String houseMaxArea) {
        this.houseMaxArea = houseMaxArea;
    }

    public NewHouseAddFavoriteRequest houseMinArea(String houseMinArea) {
        this.houseMinArea = houseMinArea;
        return this;
    }

    /**
     * Get houseMinArea
     *
     * @return houseMinArea
     **/
    @ApiModelProperty(value = "")


    public String getHouseMinArea() {
        return houseMinArea;
    }

    public void setHouseMinArea(String houseMinArea) {
        this.houseMinArea = houseMinArea;
    }

    public NewHouseAddFavoriteRequest isDel(Integer isDel) {
        this.isDel = isDel;
        return this;
    }

    /**
     * Get isDel
     *
     * @return isDel
     **/
    @ApiModelProperty(value = "")


    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public NewHouseAddFavoriteRequest status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     **/
    @ApiModelProperty(value = "")


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public NewHouseAddFavoriteRequest totalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    /**
     * Get totalPrice
     *
     * @return totalPrice
     **/
    @ApiModelProperty(value = "")

    @Valid

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public NewHouseAddFavoriteRequest userId(Integer userId) {
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
        NewHouseAddFavoriteRequest newHouseAddFavoriteRequest = (NewHouseAddFavoriteRequest) o;
        return Objects.equals(this.averagePrice, newHouseAddFavoriteRequest.averagePrice) &&
                Objects.equals(this.buildingId, newHouseAddFavoriteRequest.buildingId) &&
                Objects.equals(this.buildingName, newHouseAddFavoriteRequest.buildingName) &&
                Objects.equals(this.buildingTitleImg, newHouseAddFavoriteRequest.buildingTitleImg) &&
                Objects.equals(this.houseMaxArea, newHouseAddFavoriteRequest.houseMaxArea) &&
                Objects.equals(this.houseMinArea, newHouseAddFavoriteRequest.houseMinArea) &&
                Objects.equals(this.isDel, newHouseAddFavoriteRequest.isDel) &&
                Objects.equals(this.status, newHouseAddFavoriteRequest.status) &&
                Objects.equals(this.totalPrice, newHouseAddFavoriteRequest.totalPrice) &&
                Objects.equals(this.userId, newHouseAddFavoriteRequest.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(averagePrice, buildingId, buildingName, buildingTitleImg, houseMaxArea, houseMinArea, isDel, status, totalPrice, userId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NewHouseAddFavoriteRequest {\n");

        sb.append("    averagePrice: ").append(toIndentedString(averagePrice)).append("\n");
        sb.append("    buildingId: ").append(toIndentedString(buildingId)).append("\n");
        sb.append("    buildingName: ").append(toIndentedString(buildingName)).append("\n");
        sb.append("    buildingTitleImg: ").append(toIndentedString(buildingTitleImg)).append("\n");
        sb.append("    houseMaxArea: ").append(toIndentedString(houseMaxArea)).append("\n");
        sb.append("    houseMinArea: ").append(toIndentedString(houseMinArea)).append("\n");
        sb.append("    isDel: ").append(toIndentedString(isDel)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    totalPrice: ").append(toIndentedString(totalPrice)).append("\n");
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

