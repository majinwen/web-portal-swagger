package com.toutiao.appV2.model.suggest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.web.common.assertUtils.HouseTypeValidator;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * SuggestRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T06:27:39.209Z")

public class SuggestRequest {
    @JsonProperty("keyword")
    @NotEmpty(message = "缺少关键字")
    @ApiModelProperty(value = "关键字")
    private String keyword = null;

    @JsonProperty("property")
    @HouseTypeValidator(value = "plot,sellhouse,newhouse,rent", message = "房源路径类型错误")
    @ApiModelProperty(value = "房源类型新房newhouse 小区plot 二手房sellhouse 租房rent 首页不传")
    private String property = null;

    public SuggestRequest keyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    /**
     * 关键字
     *
     * @return keyword
     **/
//  @ApiModelProperty(value = "关键字")
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public SuggestRequest property(String property) {
        this.property = property;
        return this;
    }

    /**
     * 房源类型
     *
     * @return property
     **/
//  @ApiModelProperty(value = "房源类型")
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SuggestRequest suggestRequest = (SuggestRequest) o;
        return Objects.equals(this.keyword, suggestRequest.keyword) &&
                Objects.equals(this.property, suggestRequest.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyword, property);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SuggestRequest {\n");

        sb.append("    keyword: ").append(toIndentedString(keyword)).append("\n");
        sb.append("    property: ").append(toIndentedString(property)).append("\n");
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

