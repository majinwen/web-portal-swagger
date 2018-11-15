package com.toutiao.appV2.model.favorite;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.favorite.rent.RentFavoriteDo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * RentFavoriteListResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T08:21:25.634Z")

public class RentFavoriteListResponse {
    @JsonProperty("data")
    @Valid
    private List<RentFavoriteDo> data = null;

    @JsonProperty("totalNum")
    private Long totalNum = null;

    public RentFavoriteListResponse data(List<RentFavoriteDo> data) {
        this.data = data;
        return this;
    }

    public RentFavoriteListResponse addDataItem(RentFavoriteDo dataItem) {
        if (this.data == null) {
            this.data = new ArrayList<RentFavoriteDo>();
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

    public List<RentFavoriteDo> getData() {
        return data;
    }

    public void setData(List<RentFavoriteDo> data) {
        this.data = data;
    }

    public RentFavoriteListResponse totalNum(Long totalNum) {
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
        RentFavoriteListResponse rentFavoriteListResponse = (RentFavoriteListResponse) o;
        return Objects.equals(this.data, rentFavoriteListResponse.data) &&
                Objects.equals(this.totalNum, rentFavoriteListResponse.totalNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, totalNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RentFavoriteListResponse {\n");

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

