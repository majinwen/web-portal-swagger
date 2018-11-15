package com.toutiao.appV2.model.favorite;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * UserCenterFavoriteCountResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T08:21:25.634Z")

public class UserCenterFavoriteCountResponse {
    @JsonProperty("esfHouseFavoriteCount")
    private Integer esfHouseFavoriteCount = null;

    @JsonProperty("newHouseFavoriteCount")
    private Integer newHouseFavoriteCount = null;

    @JsonProperty("plotFavoriteCount")
    private Integer plotFavoriteCount = null;

    @JsonProperty("rentHouseFavoriteCount")
    private Integer rentHouseFavoriteCount = null;

    public UserCenterFavoriteCountResponse esfHouseFavoriteCount(Integer esfHouseFavoriteCount) {
        this.esfHouseFavoriteCount = esfHouseFavoriteCount;
        return this;
    }

    /**
     * Get esfHouseFavoriteCount
     *
     * @return esfHouseFavoriteCount
     **/
    @ApiModelProperty(value = "")


    public Integer getEsfHouseFavoriteCount() {
        return esfHouseFavoriteCount;
    }

    public void setEsfHouseFavoriteCount(Integer esfHouseFavoriteCount) {
        this.esfHouseFavoriteCount = esfHouseFavoriteCount;
    }

    public UserCenterFavoriteCountResponse newHouseFavoriteCount(Integer newHouseFavoriteCount) {
        this.newHouseFavoriteCount = newHouseFavoriteCount;
        return this;
    }

    /**
     * Get newHouseFavoriteCount
     *
     * @return newHouseFavoriteCount
     **/
    @ApiModelProperty(value = "")


    public Integer getNewHouseFavoriteCount() {
        return newHouseFavoriteCount;
    }

    public void setNewHouseFavoriteCount(Integer newHouseFavoriteCount) {
        this.newHouseFavoriteCount = newHouseFavoriteCount;
    }

    public UserCenterFavoriteCountResponse plotFavoriteCount(Integer plotFavoriteCount) {
        this.plotFavoriteCount = plotFavoriteCount;
        return this;
    }

    /**
     * Get plotFavoriteCount
     *
     * @return plotFavoriteCount
     **/
    @ApiModelProperty(value = "")


    public Integer getPlotFavoriteCount() {
        return plotFavoriteCount;
    }

    public void setPlotFavoriteCount(Integer plotFavoriteCount) {
        this.plotFavoriteCount = plotFavoriteCount;
    }

    public UserCenterFavoriteCountResponse rentHouseFavoriteCount(Integer rentHouseFavoriteCount) {
        this.rentHouseFavoriteCount = rentHouseFavoriteCount;
        return this;
    }

    /**
     * Get rentHouseFavoriteCount
     *
     * @return rentHouseFavoriteCount
     **/
    @ApiModelProperty(value = "")


    public Integer getRentHouseFavoriteCount() {
        return rentHouseFavoriteCount;
    }

    public void setRentHouseFavoriteCount(Integer rentHouseFavoriteCount) {
        this.rentHouseFavoriteCount = rentHouseFavoriteCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserCenterFavoriteCountResponse userCenterFavoriteCountResponse = (UserCenterFavoriteCountResponse) o;
        return Objects.equals(this.esfHouseFavoriteCount, userCenterFavoriteCountResponse.esfHouseFavoriteCount) &&
                Objects.equals(this.newHouseFavoriteCount, userCenterFavoriteCountResponse.newHouseFavoriteCount) &&
                Objects.equals(this.plotFavoriteCount, userCenterFavoriteCountResponse.plotFavoriteCount) &&
                Objects.equals(this.rentHouseFavoriteCount, userCenterFavoriteCountResponse.rentHouseFavoriteCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(esfHouseFavoriteCount, newHouseFavoriteCount, plotFavoriteCount, rentHouseFavoriteCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserCenterFavoriteCountResponse {\n");

        sb.append("    esfHouseFavoriteCount: ").append(toIndentedString(esfHouseFavoriteCount)).append("\n");
        sb.append("    newHouseFavoriteCount: ").append(toIndentedString(newHouseFavoriteCount)).append("\n");
        sb.append("    plotFavoriteCount: ").append(toIndentedString(plotFavoriteCount)).append("\n");
        sb.append("    rentHouseFavoriteCount: ").append(toIndentedString(rentHouseFavoriteCount)).append("\n");
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

