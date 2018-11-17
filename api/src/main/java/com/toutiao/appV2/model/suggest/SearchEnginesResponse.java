package com.toutiao.appV2.model.suggest;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SearchEnginesResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T06:17:59.507Z")

public class SearchEnginesResponse {
    @JsonProperty("area")
    private String area = null;

    @JsonProperty("areaId")
    private Integer areaId = null;

    @JsonProperty("cityId")
    private Integer cityId = null;

    @JsonProperty("district")
    private String district = null;

    @JsonProperty("districtId")
    private Integer districtId = null;

    @JsonProperty("isApprove")
    private Integer isApprove = null;

    @JsonProperty("isDel")
    private Integer isDel = null;

    @JsonProperty("searchId")
    private Integer searchId = null;

    @JsonProperty("searchName")
    private String searchName = null;

    @JsonProperty("searchNickname")
    @Valid
    private List<String> searchNickname = null;

    @JsonProperty("searchSort")
    private String searchSort = null;

    @JsonProperty("searchType")
    private String searchType = null;

    @JsonProperty("searchTypeSings")
    private Integer searchTypeSings = null;

    public SearchEnginesResponse area(String area) {
        this.area = area;
        return this;
    }

    /**
     * 商圈名称
     *
     * @return area
     **/
    @ApiModelProperty(value = "商圈名称")


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public SearchEnginesResponse areaId(Integer areaId) {
        this.areaId = areaId;
        return this;
    }

    /**
     * 商圈id
     *
     * @return areaId
     **/
    @ApiModelProperty(value = "商圈id")


    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public SearchEnginesResponse cityId(Integer cityId) {
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

    public SearchEnginesResponse district(String district) {
        this.district = district;
        return this;
    }

    /**
     * 区县名称
     *
     * @return district
     **/
    @ApiModelProperty(value = "区县名称")


    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public SearchEnginesResponse districtId(Integer districtId) {
        this.districtId = districtId;
        return this;
    }

    /**
     * 区县id
     *
     * @return districtId
     **/
    @ApiModelProperty(value = "区县id")


    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public SearchEnginesResponse isApprove(Integer isApprove) {
        this.isApprove = isApprove;
        return this;
    }

    /**
     * 是否发布
     *
     * @return isApprove
     **/
    @ApiModelProperty(value = "是否发布")


    public Integer getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Integer isApprove) {
        this.isApprove = isApprove;
    }

    public SearchEnginesResponse isDel(Integer isDel) {
        this.isDel = isDel;
        return this;
    }

    /**
     * 是否删除
     *
     * @return isDel
     **/
    @ApiModelProperty(value = "是否删除")


    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public SearchEnginesResponse searchId(Integer searchId) {
        this.searchId = searchId;
        return this;
    }

    /**
     * 房源id
     *
     * @return searchId
     **/
    @ApiModelProperty(value = "房源id")


    public Integer getSearchId() {
        return searchId;
    }

    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }

    public SearchEnginesResponse searchName(String searchName) {
        this.searchName = searchName;
        return this;
    }

    /**
     * 房源名称
     *
     * @return searchName
     **/
    @ApiModelProperty(value = "房源名称")


    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public SearchEnginesResponse searchNickname(List<String> searchNickname) {
        this.searchNickname = searchNickname;
        return this;
    }

    public SearchEnginesResponse addSearchNicknameItem(String searchNicknameItem) {
        if (this.searchNickname == null) {
            this.searchNickname = new ArrayList<String>();
        }
        this.searchNickname.add(searchNicknameItem);
        return this;
    }

    /**
     * 房源别名
     *
     * @return searchNickname
     **/
    @ApiModelProperty(value = "房源别名")


    public List<String> getSearchNickname() {
        return searchNickname;
    }

    public void setSearchNickname(List<String> searchNickname) {
        this.searchNickname = searchNickname;
    }

    public SearchEnginesResponse searchSort(String searchSort) {
        this.searchSort = searchSort;
        return this;
    }

    /**
     * 排序名称
     *
     * @return searchSort
     **/
    @ApiModelProperty(value = "排序名称")


    public String getSearchSort() {
        return searchSort;
    }

    public void setSearchSort(String searchSort) {
        this.searchSort = searchSort;
    }

    public SearchEnginesResponse searchType(String searchType) {
        this.searchType = searchType;
        return this;
    }

    /**
     * 房屋类型
     *
     * @return searchType
     **/
    @ApiModelProperty(value = "房屋类型")


    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public SearchEnginesResponse searchTypeSings(Integer searchTypeSings) {
        this.searchTypeSings = searchTypeSings;
        return this;
    }

    /**
     * 房屋类型标志
     *
     * @return searchTypeSings
     **/
    @ApiModelProperty(value = "房屋类型标志")


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
        SearchEnginesResponse searchEnginesResponse = (SearchEnginesResponse) o;
        return Objects.equals(this.area, searchEnginesResponse.area) &&
                Objects.equals(this.areaId, searchEnginesResponse.areaId) &&
                Objects.equals(this.cityId, searchEnginesResponse.cityId) &&
                Objects.equals(this.district, searchEnginesResponse.district) &&
                Objects.equals(this.districtId, searchEnginesResponse.districtId) &&
                Objects.equals(this.isApprove, searchEnginesResponse.isApprove) &&
                Objects.equals(this.isDel, searchEnginesResponse.isDel) &&
                Objects.equals(this.searchId, searchEnginesResponse.searchId) &&
                Objects.equals(this.searchName, searchEnginesResponse.searchName) &&
                Objects.equals(this.searchNickname, searchEnginesResponse.searchNickname) &&
                Objects.equals(this.searchSort, searchEnginesResponse.searchSort) &&
                Objects.equals(this.searchType, searchEnginesResponse.searchType) &&
                Objects.equals(this.searchTypeSings, searchEnginesResponse.searchTypeSings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(area, areaId, cityId, district, districtId, isApprove, isDel, searchId, searchName, searchNickname, searchSort, searchType, searchTypeSings);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SearchEnginesResponse {\n");

        sb.append("    area: ").append(toIndentedString(area)).append("\n");
        sb.append("    areaId: ").append(toIndentedString(areaId)).append("\n");
        sb.append("    cityId: ").append(toIndentedString(cityId)).append("\n");
        sb.append("    district: ").append(toIndentedString(district)).append("\n");
        sb.append("    districtId: ").append(toIndentedString(districtId)).append("\n");
        sb.append("    isApprove: ").append(toIndentedString(isApprove)).append("\n");
        sb.append("    isDel: ").append(toIndentedString(isDel)).append("\n");
        sb.append("    searchId: ").append(toIndentedString(searchId)).append("\n");
        sb.append("    searchName: ").append(toIndentedString(searchName)).append("\n");
        sb.append("    searchNickname: ").append(toIndentedString(searchNickname)).append("\n");
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

