package com.toutiao.appV2.model.suggest;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SuggestResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T06:17:59.507Z")

public class SuggestResponse {
    @JsonProperty("apartmentNum")
    private Integer apartmentNum = null;

    @JsonProperty("esfNum")
    private Integer esfNum = null;

    @JsonProperty("newHouseNum")
    private Integer newHouseNum = null;

    @JsonProperty("plotNum")
    private Integer plotNum = null;

    @JsonProperty("rentNum")
    private Integer rentNum = null;

    @JsonProperty("searchEnginesList")
    @Valid
    private List<SearchEnginesResponse> searchEnginesList = null;

    @JsonProperty("searchScopeList")
    @Valid
    private List<SearchScopeResponse> searchScopeList = null;

    public SuggestResponse apartmentNum(Integer apartmentNum) {
        this.apartmentNum = apartmentNum;
        return this;
    }

    /**
     * 公寓个数
     *
     * @return apartmentNum
     **/
    @ApiModelProperty(value = "公寓个数")


    public Integer getApartmentNum() {
        return apartmentNum;
    }

    public void setApartmentNum(Integer apartmentNum) {
        this.apartmentNum = apartmentNum;
    }

    public SuggestResponse esfNum(Integer esfNum) {
        this.esfNum = esfNum;
        return this;
    }

    /**
     * 二手房个数
     *
     * @return esfNum
     **/
    @ApiModelProperty(value = "二手房个数")


    public Integer getEsfNum() {
        return esfNum;
    }

    public void setEsfNum(Integer esfNum) {
        this.esfNum = esfNum;
    }

    public SuggestResponse newHouseNum(Integer newHouseNum) {
        this.newHouseNum = newHouseNum;
        return this;
    }

    /**
     * 新房个数
     *
     * @return newHouseNum
     **/
    @ApiModelProperty(value = "新房个数")


    public Integer getNewHouseNum() {
        return newHouseNum;
    }

    public void setNewHouseNum(Integer newHouseNum) {
        this.newHouseNum = newHouseNum;
    }

    public SuggestResponse plotNum(Integer plotNum) {
        this.plotNum = plotNum;
        return this;
    }

    /**
     * 小区个数
     *
     * @return plotNum
     **/
    @ApiModelProperty(value = "小区个数")


    public Integer getPlotNum() {
        return plotNum;
    }

    public void setPlotNum(Integer plotNum) {
        this.plotNum = plotNum;
    }

    public SuggestResponse rentNum(Integer rentNum) {
        this.rentNum = rentNum;
        return this;
    }

    /**
     * 普租个数
     *
     * @return rentNum
     **/
    @ApiModelProperty(value = "普租个数")


    public Integer getRentNum() {
        return rentNum;
    }

    public void setRentNum(Integer rentNum) {
        this.rentNum = rentNum;
    }

    public SuggestResponse searchEnginesList(List<SearchEnginesResponse> searchEnginesList) {
        this.searchEnginesList = searchEnginesList;
        return this;
    }

    public SuggestResponse addSearchEnginesListItem(SearchEnginesResponse searchEnginesListItem) {
        if (this.searchEnginesList == null) {
            this.searchEnginesList = new ArrayList<SearchEnginesResponse>();
        }
        this.searchEnginesList.add(searchEnginesListItem);
        return this;
    }

    /**
     * 关键词
     *
     * @return searchEnginesList
     **/
    @ApiModelProperty(value = "关键词")

    @Valid

    public List<SearchEnginesResponse> getSearchEnginesList() {
        return searchEnginesList;
    }

    public void setSearchEnginesList(List<SearchEnginesResponse> searchEnginesList) {
        this.searchEnginesList = searchEnginesList;
    }

    public SuggestResponse searchScopeList(List<SearchScopeResponse> searchScopeList) {
        this.searchScopeList = searchScopeList;
        return this;
    }

    public SuggestResponse addSearchScopeListItem(SearchScopeResponse searchScopeListItem) {
        if (this.searchScopeList == null) {
            this.searchScopeList = new ArrayList<SearchScopeResponse>();
        }
        this.searchScopeList.add(searchScopeListItem);
        return this;
    }

    /**
     * 区域商圈
     *
     * @return searchScopeList
     **/
    @ApiModelProperty(value = "区域商圈")

    @Valid

    public List<SearchScopeResponse> getSearchScopeList() {
        return searchScopeList;
    }

    public void setSearchScopeList(List<SearchScopeResponse> searchScopeList) {
        this.searchScopeList = searchScopeList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SuggestResponse suggestResponse = (SuggestResponse) o;
        return Objects.equals(this.apartmentNum, suggestResponse.apartmentNum) &&
                Objects.equals(this.esfNum, suggestResponse.esfNum) &&
                Objects.equals(this.newHouseNum, suggestResponse.newHouseNum) &&
                Objects.equals(this.plotNum, suggestResponse.plotNum) &&
                Objects.equals(this.rentNum, suggestResponse.rentNum) &&
                Objects.equals(this.searchEnginesList, suggestResponse.searchEnginesList) &&
                Objects.equals(this.searchScopeList, suggestResponse.searchScopeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apartmentNum, esfNum, newHouseNum, plotNum, rentNum, searchEnginesList, searchScopeList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SuggestResponse {\n");

        sb.append("    apartmentNum: ").append(toIndentedString(apartmentNum)).append("\n");
        sb.append("    esfNum: ").append(toIndentedString(esfNum)).append("\n");
        sb.append("    newHouseNum: ").append(toIndentedString(newHouseNum)).append("\n");
        sb.append("    plotNum: ").append(toIndentedString(plotNum)).append("\n");
        sb.append("    rentNum: ").append(toIndentedString(rentNum)).append("\n");
        sb.append("    searchEnginesList: ").append(toIndentedString(searchEnginesList)).append("\n");
        sb.append("    searchScopeList: ").append(toIndentedString(searchScopeList)).append("\n");
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

