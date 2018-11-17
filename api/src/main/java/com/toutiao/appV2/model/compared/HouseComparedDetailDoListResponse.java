package com.toutiao.appV2.model.compared;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.compared.HouseComparedDetailDo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * HouseComparedDetailDoListResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T07:53:35.212Z")

public class HouseComparedDetailDoListResponse {
    @JsonProperty("data")
    @Valid
    private List<HouseComparedDetailDo> data = null;

    @JsonProperty("totalNum")
    private Integer totalNum = null;

    public HouseComparedDetailDoListResponse data(List<HouseComparedDetailDo> data) {
        this.data = data;
        return this;
    }

    public HouseComparedDetailDoListResponse addDataItem(HouseComparedDetailDo dataItem) {
        if (this.data == null) {
            this.data = new ArrayList<HouseComparedDetailDo>();
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

    public List<HouseComparedDetailDo> getData() {
        return data;
    }

    public void setData(List<HouseComparedDetailDo> data) {
        this.data = data;
    }

    public HouseComparedDetailDoListResponse totalNum(Integer totalNum) {
        this.totalNum = totalNum;
        return this;
    }

    /**
     * Get totalNum
     *
     * @return totalNum
     **/
    @ApiModelProperty(value = "")


    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
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
        HouseComparedDetailDoListResponse houseComparedDetailDoListResponse = (HouseComparedDetailDoListResponse) o;
        return Objects.equals(this.data, houseComparedDetailDoListResponse.data) &&
                Objects.equals(this.totalNum, houseComparedDetailDoListResponse.totalNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, totalNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class HouseComparedDetailDoListResponse {\n");

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

