package com.toutiao.appV2.model.HomePage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.toutiao.app.api.chance.response.homepage.HomePageMustBuyResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * HomePageMustBuyList
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T05:52:08.771Z")

public class HomePageMustBuyList   {
    @JsonProperty("homePageMustBuyResponses")
    @Valid
    private List<HomePageMustBuyResponse> homePageMustBuyResponses = null;

    @JsonProperty("total")
    private Integer total = null;

    public HomePageMustBuyList homePageMustBuyResponses(List<HomePageMustBuyResponse> homePageMustBuyResponses) {
        this.homePageMustBuyResponses = homePageMustBuyResponses;
        return this;
    }

    public HomePageMustBuyList addHomePageMustBuyResponsesItem(HomePageMustBuyResponse homePageMustBuyResponsesItem) {
        if (this.homePageMustBuyResponses == null) {
            this.homePageMustBuyResponses = new ArrayList<HomePageMustBuyResponse>();
        }
        this.homePageMustBuyResponses.add(homePageMustBuyResponsesItem);
        return this;
    }

    /**
     * Get homePageMustBuyResponses
     * @return homePageMustBuyResponses
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<HomePageMustBuyResponse> getHomePageMustBuyResponses() {
        return homePageMustBuyResponses;
    }

    public void setHomePageMustBuyResponses(List<HomePageMustBuyResponse> homePageMustBuyResponses) {
        this.homePageMustBuyResponses = homePageMustBuyResponses;
    }

    public HomePageMustBuyList total(Integer total) {
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
        HomePageMustBuyList homePageMustBuyList = (HomePageMustBuyList) o;
        return Objects.equals(this.homePageMustBuyResponses, homePageMustBuyList.homePageMustBuyResponses) &&
                Objects.equals(this.total, homePageMustBuyList.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePageMustBuyResponses, total);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class HomePageMustBuyList {\n");

        sb.append("    homePageMustBuyResponses: ").append(toIndentedString(homePageMustBuyResponses)).append("\n");
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

