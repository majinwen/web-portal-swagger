package com.toutiao.appV2.model.favorite;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteDo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * NewHouseFavoriteListResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T08:21:25.634Z")

public class NewHouseFavoriteListResponse {
    @JsonProperty("data")
    @Valid
    private List<NewHouseFavoriteDo> data = null;

    @JsonProperty("totalNum")
    private Long totalNum = null;

    public NewHouseFavoriteListResponse data(List<NewHouseFavoriteDo> data) {
        this.data = data;
        return this;
    }

    public NewHouseFavoriteListResponse addDataItem(NewHouseFavoriteDo dataItem) {
        if (this.data == null) {
            this.data = new ArrayList<NewHouseFavoriteDo>();
        }
        this.data.add(dataItem);
        return this;
    }

    /**
     * Get data
     *
     * @return data
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<NewHouseFavoriteDo> getData() {
        return data;
    }

    public void setData(List<NewHouseFavoriteDo> data) {
        this.data = data;
    }

    public NewHouseFavoriteListResponse totalNum(Long totalNum) {
        this.totalNum = totalNum;
        return this;
    }

    /**
     * Get totalNum
     *
     * @return totalNum
     **/
    @ApiModelProperty(value = "")


    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NewHouseFavoriteListResponse newHouseFavoriteListResponse = (NewHouseFavoriteListResponse) o;
        return Objects.equals(this.data, newHouseFavoriteListResponse.data) &&
                Objects.equals(this.totalNum, newHouseFavoriteListResponse.totalNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, totalNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NewHouseFavoriteListResponse {\n");

        sb.append("    data: ").append(toIndentedString(data)).append("\n");
        sb.append("    totalNum: ").append(toIndentedString(totalNum)).append("\n");
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

