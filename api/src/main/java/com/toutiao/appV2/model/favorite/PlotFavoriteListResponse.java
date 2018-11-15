package com.toutiao.appV2.model.favorite;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.plot.UserFavoritePlotDo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PlotFavoriteListResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T08:21:25.634Z")

public class PlotFavoriteListResponse {
    @JsonProperty("data")
    @Valid
    private List<UserFavoritePlotDo> data = null;

    @JsonProperty("totalNum")
    private Long totalNum = null;

    public PlotFavoriteListResponse data(List<UserFavoritePlotDo> data) {
        this.data = data;
        return this;
    }

    public PlotFavoriteListResponse addDataItem(UserFavoritePlotDo dataItem) {
        if (this.data == null) {
            this.data = new ArrayList<UserFavoritePlotDo>();
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

    public List<UserFavoritePlotDo> getData() {
        return data;
    }

    public void setData(List<UserFavoritePlotDo> data) {
        this.data = data;
    }

    public PlotFavoriteListResponse totalNum(Long totalNum) {
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
        PlotFavoriteListResponse plotFavoriteListResponse = (PlotFavoriteListResponse) o;
        return Objects.equals(this.data, plotFavoriteListResponse.data) &&
                Objects.equals(this.totalNum, plotFavoriteListResponse.totalNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, totalNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PlotFavoriteListResponse {\n");

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

