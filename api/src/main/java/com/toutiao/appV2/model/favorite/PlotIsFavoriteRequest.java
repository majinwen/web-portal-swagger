package com.toutiao.appV2.model.favorite;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * PlotIsFavoriteRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T08:21:25.634Z")

public class PlotIsFavoriteRequest {
    @JsonProperty("buildingId")
    private Integer buildingId = null;

    @JsonProperty("userId")
    private Integer userId = null;

    public PlotIsFavoriteRequest buildingId(Integer buildingId) {
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

    public PlotIsFavoriteRequest userId(Integer userId) {
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
        PlotIsFavoriteRequest plotIsFavoriteRequest = (PlotIsFavoriteRequest) o;
        return Objects.equals(this.buildingId, plotIsFavoriteRequest.buildingId) &&
                Objects.equals(this.userId, plotIsFavoriteRequest.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingId, userId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PlotIsFavoriteRequest {\n");

        sb.append("    buildingId: ").append(toIndentedString(buildingId)).append("\n");
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

