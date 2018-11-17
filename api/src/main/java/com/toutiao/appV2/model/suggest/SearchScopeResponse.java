package com.toutiao.appV2.model.suggest;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * SearchScopeResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T06:17:59.507Z")

public class SearchScopeResponse {
    @JsonProperty("cityId")
    private Integer cityId = null;

    @JsonProperty("locationNum")
    private Integer locationNum = null;

    @JsonProperty("locationType")
    private String locationType = null;

    @JsonProperty("locationTypeSings")
    private Integer locationTypeSings = null;

    @JsonProperty("searchId")
    private Integer searchId = null;

    @JsonProperty("searchName")
    private String searchName = null;

    @JsonProperty("searchSort")
    private String searchSort = null;

    @JsonProperty("searchType")
    private String searchType = null;

    @JsonProperty("searchTypeSings")
    private Integer searchTypeSings = null;

    public SearchScopeResponse cityId(Integer cityId) {
        this.cityId = cityId;
        return this;
    }

    /**
     * 城市id
     *
     * @return cityId
     **/
    @ApiModelProperty(value = "城市id")


    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public SearchScopeResponse locationNum(Integer locationNum) {
        this.locationNum = locationNum;
        return this;
    }

    /**
     * 该区县或商圈下对应房源的个数
     *
     * @return locationNum
     **/
    @ApiModelProperty(value = "该区县或商圈下对应房源的个数")


    public Integer getLocationNum() {
        return locationNum;
    }

    public void setLocationNum(Integer locationNum) {
        this.locationNum = locationNum;
    }

    public SearchScopeResponse locationType(String locationType) {
        this.locationType = locationType;
        return this;
    }

    /**
     * 区县或者商圈的类型标志名称（e.g. 区县，商圈）
     *
     * @return locationType
     **/
    @ApiModelProperty(value = "区县或者商圈的类型标志名称（e.g. 区县，商圈）")


    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public SearchScopeResponse locationTypeSings(Integer locationTypeSings) {
        this.locationTypeSings = locationTypeSings;
        return this;
    }

    /**
     * 区县或者商圈的类型标志（1-区县，2-商圈）数
     *
     * @return locationTypeSings
     **/
    @ApiModelProperty(value = "区县或者商圈的类型标志（1-区县，2-商圈）数")


    public Integer getLocationTypeSings() {
        return locationTypeSings;
    }

    public void setLocationTypeSings(Integer locationTypeSings) {
        this.locationTypeSings = locationTypeSings;
    }

    public SearchScopeResponse searchId(Integer searchId) {
        this.searchId = searchId;
        return this;
    }

    /**
     * 区县或者商圈id
     *
     * @return searchId
     **/
    @ApiModelProperty(value = "区县或者商圈id")


    public Integer getSearchId() {
        return searchId;
    }

    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }

    public SearchScopeResponse searchName(String searchName) {
        this.searchName = searchName;
        return this;
    }

    /**
     * 区县或者商圈类型
     *
     * @return searchName
     **/
    @ApiModelProperty(value = "区县或者商圈类型")


    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public SearchScopeResponse searchSort(String searchSort) {
        this.searchSort = searchSort;
        return this;
    }

    /**
     * 区县或者商圈的名称（用于排序）
     *
     * @return searchSort
     **/
    @ApiModelProperty(value = "区县或者商圈的名称（用于排序）")


    public String getSearchSort() {
        return searchSort;
    }

    public void setSearchSort(String searchSort) {
        this.searchSort = searchSort;
    }

    public SearchScopeResponse searchType(String searchType) {
        this.searchType = searchType;
        return this;
    }

    /**
     * 房屋类型名称：新房，小区，二手房, 普租, 公寓
     *
     * @return searchType
     **/
    @ApiModelProperty(value = "房屋类型名称：新房，小区，二手房, 普租, 公寓")


    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public SearchScopeResponse searchTypeSings(Integer searchTypeSings) {
        this.searchTypeSings = searchTypeSings;
        return this;
    }

    /**
     * 房屋类型名称标志：0-新房，1-小区，2-二手房, 3-普租, 4-公寓
     *
     * @return searchTypeSings
     **/
    @ApiModelProperty(value = "房屋类型名称标志：0-新房，1-小区，2-二手房, 3-普租, 4-公寓")


    public Integer getSearchTypeSings() {
        return searchTypeSings;
    }

    public void setSearchTypeSings(Integer searchTypeSings) {
        this.searchTypeSings = searchTypeSings;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SearchScopeResponse searchScopeResponse = (SearchScopeResponse) o;
        return Objects.equals(this.cityId, searchScopeResponse.cityId) &&
                Objects.equals(this.locationNum, searchScopeResponse.locationNum) &&
                Objects.equals(this.locationType, searchScopeResponse.locationType) &&
                Objects.equals(this.locationTypeSings, searchScopeResponse.locationTypeSings) &&
                Objects.equals(this.searchId, searchScopeResponse.searchId) &&
                Objects.equals(this.searchName, searchScopeResponse.searchName) &&
                Objects.equals(this.searchSort, searchScopeResponse.searchSort) &&
                Objects.equals(this.searchType, searchScopeResponse.searchType) &&
                Objects.equals(this.searchTypeSings, searchScopeResponse.searchTypeSings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, locationNum, locationType, locationTypeSings, searchId, searchName, searchSort, searchType, searchTypeSings);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SearchScopeResponse {\n");

        sb.append("    cityId: ").append(toIndentedString(cityId)).append("\n");
        sb.append("    locationNum: ").append(toIndentedString(locationNum)).append("\n");
        sb.append("    locationType: ").append(toIndentedString(locationType)).append("\n");
        sb.append("    locationTypeSings: ").append(toIndentedString(locationTypeSings)).append("\n");
        sb.append("    searchId: ").append(toIndentedString(searchId)).append("\n");
        sb.append("    searchName: ").append(toIndentedString(searchName)).append("\n");
        sb.append("    searchSort: ").append(toIndentedString(searchSort)).append("\n");
        sb.append("    searchType: ").append(toIndentedString(searchType)).append("\n");
        sb.append("    searchTypeSings: ").append(toIndentedString(searchTypeSings)).append("\n");
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

