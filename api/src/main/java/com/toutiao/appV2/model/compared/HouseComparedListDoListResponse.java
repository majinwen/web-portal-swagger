package com.toutiao.appV2.model.compared;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.compared.HouseComparedListDo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * HouseComparedListDoListResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T07:53:35.212Z")

public class HouseComparedListDoListResponse {
    @JsonProperty("data")
    @Valid
    private List<HouseComparedListDo> data = null;

    @JsonProperty("totalNum")
    private Integer totalNum = null;

    public HouseComparedListDoListResponse data(List<HouseComparedListDo> data) {
        this.data = data;
        return this;
    }

    public HouseComparedListDoListResponse addDataItem(HouseComparedListDo dataItem) {
        if (this.data == null) {
            this.data = new ArrayList<HouseComparedListDo>();
        }
        this.data.add(dataItem);
        return this;
    }

    /**
     * 对比房源列表
     *
     * @return data
     **/
    @ApiModelProperty(value = "对比房源列表")

    @Valid

    public List<HouseComparedListDo> getData() {
        return data;
    }

    public void setData(List<HouseComparedListDo> data) {
        this.data = data;
    }

    public HouseComparedListDoListResponse totalNum(Integer totalNum) {
        this.totalNum = totalNum;
        return this;
    }

    /**
     * 总数
     *
     * @return totalNum
     **/
    @ApiModelProperty(value = "总数")


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
        HouseComparedListDoListResponse houseComparedListDoListResponse = (HouseComparedListDoListResponse) o;
        return Objects.equals(this.data, houseComparedListDoListResponse.data) &&
                Objects.equals(this.totalNum, houseComparedListDoListResponse.totalNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, totalNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class HouseComparedListDoListResponse {\n");

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

