package com.toutiao.appV2.model.favorite;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CancelFavoriteRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T08:21:25.634Z")

public class CancelFavoriteRequest {
    @JsonProperty("buildingId")
    private Integer buildingId = null;

    @JsonProperty("userId")
    private Integer userId = null;

    @JsonProperty("villageId")
    private Integer villageId = null;

    public CancelFavoriteRequest buildingId(Integer buildingId) {
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

    public CancelFavoriteRequest userId(Integer userId) {
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

    public CancelFavoriteRequest villageId(Integer villageId) {
        this.villageId = villageId;
        return this;
    }

    /**
     * Get villageId
     *
     * @return villageId
     **/
    @ApiModelProperty(value = "")


    public Integer getVillageId() {
        return villageId;
    }

    public void setVillageId(Integer villageId) {
        this.villageId = villageId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CancelFavoriteRequest cancelFavoriteRequest = (CancelFavoriteRequest) o;
        return Objects.equals(this.buildingId, cancelFavoriteRequest.buildingId) &&
                Objects.equals(this.userId, cancelFavoriteRequest.userId) &&
                Objects.equals(this.villageId, cancelFavoriteRequest.villageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingId, userId, villageId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CancelFavoriteRequest {\n");

        sb.append("    buildingId: ").append(toIndentedString(buildingId)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    villageId: ").append(toIndentedString(villageId)).append("\n");
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

