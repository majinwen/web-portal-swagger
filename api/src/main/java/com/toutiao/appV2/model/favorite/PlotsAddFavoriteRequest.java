package com.toutiao.appV2.model.favorite;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * PlotsAddFavoriteRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T08:21:25.634Z")

public class PlotsAddFavoriteRequest {
    @JsonProperty("averagePrice")
    private Double averagePrice = null;

    @JsonProperty("buildingId")
    private Integer buildingId = null;

    @JsonProperty("buildingImages")
    private String buildingImages = null;

    @JsonProperty("buildingName")
    private String buildingName = null;

    @JsonProperty("isDel")
    private Integer isDel = null;

    @JsonProperty("status")
    private Integer status = null;

    @JsonProperty("userId")
    private Integer userId = null;

    public PlotsAddFavoriteRequest averagePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
        return this;
    }

    /**
     * Get averagePrice
     *
     * @return averagePrice
     **/
    @ApiModelProperty(value = "")


    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public PlotsAddFavoriteRequest buildingId(Integer buildingId) {
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

    public PlotsAddFavoriteRequest buildingImages(String buildingImages) {
        this.buildingImages = buildingImages;
        return this;
    }

    /**
     * Get buildingImages
     *
     * @return buildingImages
     **/
    @ApiModelProperty(value = "")


    public String getBuildingImages() {
        return buildingImages;
    }

    public void setBuildingImages(String buildingImages) {
        this.buildingImages = buildingImages;
    }

    public PlotsAddFavoriteRequest buildingName(String buildingName) {
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

    public PlotsAddFavoriteRequest isDel(Integer isDel) {
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

    public PlotsAddFavoriteRequest status(Integer status) {
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

    public PlotsAddFavoriteRequest userId(Integer userId) {
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
        PlotsAddFavoriteRequest plotsAddFavoriteRequest = (PlotsAddFavoriteRequest) o;
        return Objects.equals(this.averagePrice, plotsAddFavoriteRequest.averagePrice) &&
                Objects.equals(this.buildingId, plotsAddFavoriteRequest.buildingId) &&
                Objects.equals(this.buildingImages, plotsAddFavoriteRequest.buildingImages) &&
                Objects.equals(this.buildingName, plotsAddFavoriteRequest.buildingName) &&
                Objects.equals(this.isDel, plotsAddFavoriteRequest.isDel) &&
                Objects.equals(this.status, plotsAddFavoriteRequest.status) &&
                Objects.equals(this.userId, plotsAddFavoriteRequest.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(averagePrice, buildingId, buildingImages, buildingName, isDel, status, userId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PlotsAddFavoriteRequest {\n");

        sb.append("    averagePrice: ").append(toIndentedString(averagePrice)).append("\n");
        sb.append("    buildingId: ").append(toIndentedString(buildingId)).append("\n");
        sb.append("    buildingImages: ").append(toIndentedString(buildingImages)).append("\n");
        sb.append("    buildingName: ").append(toIndentedString(buildingName)).append("\n");
        sb.append("    isDel: ").append(toIndentedString(isDel)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

