package com.toutiao.appV2.model.HomePage;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.toutiao.app.api.chance.response.homepage.HomeSureToSnatchResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * HomeSureToSnatchList
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T05:52:08.771Z")

public class HomeSureToSnatchList   {
    @JsonProperty("homeSureToSnatchResponses")
    @Valid
    private List<HomeSureToSnatchResponse> homeSureToSnatchResponses = null;

    @JsonProperty("total")
    private Integer total = null;

    public HomeSureToSnatchList homeSureToSnatchResponses(List<HomeSureToSnatchResponse> homeSureToSnatchResponses) {
        this.homeSureToSnatchResponses = homeSureToSnatchResponses;
        return this;
    }

    public HomeSureToSnatchList addHomeSureToSnatchResponsesItem(HomeSureToSnatchResponse homeSureToSnatchResponsesItem) {
        if (this.homeSureToSnatchResponses == null) {
            this.homeSureToSnatchResponses = new ArrayList<HomeSureToSnatchResponse>();
        }
        this.homeSureToSnatchResponses.add(homeSureToSnatchResponsesItem);
        return this;
    }

    /**
     * Get homeSureToSnatchResponses
     * @return homeSureToSnatchResponses
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<HomeSureToSnatchResponse> getHomeSureToSnatchResponses() {
        return homeSureToSnatchResponses;
    }

    public void setHomeSureToSnatchResponses(List<HomeSureToSnatchResponse> homeSureToSnatchResponses) {
        this.homeSureToSnatchResponses = homeSureToSnatchResponses;
    }

    public HomeSureToSnatchList total(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * Get total
     * @return total
     **/
    @ApiModelProperty(value = "")


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HomeSureToSnatchList homeSureToSnatchList = (HomeSureToSnatchList) o;
        return Objects.equals(this.homeSureToSnatchResponses, homeSureToSnatchList.homeSureToSnatchResponses) &&
                Objects.equals(this.total, homeSureToSnatchList.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeSureToSnatchResponses, total);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class HomeSureToSnatchList {\n");

        sb.append("    homeSureToSnatchResponses: ").append(toIndentedString(homeSureToSnatchResponses)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

