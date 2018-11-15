package com.toutiao.appV2.model.favorite;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * SellHouseFavoriteListRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T08:21:25.634Z")

public class SellHouseFavoriteListRequest {
    @JsonProperty("pageNum")
    private Integer pageNum = null;

    @JsonProperty("size")
    private Integer size = null;

    @JsonProperty("userId")
    private Integer userId = null;

    public SellHouseFavoriteListRequest pageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    /**
     * Get pageNum
     *
     * @return pageNum
     **/
    @ApiModelProperty(value = "")


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public SellHouseFavoriteListRequest size(Integer size) {
        this.size = size;
        return this;
    }

    /**
     * Get size
     *
     * @return size
     **/
    @ApiModelProperty(value = "")


    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public SellHouseFavoriteListRequest userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Get userId
     *
     * @return userId
     **/
    @ApiModelProperty(value = "")


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SellHouseFavoriteListRequest sellHouseFavoriteListRequest = (SellHouseFavoriteListRequest) o;
        return Objects.equals(this.pageNum, sellHouseFavoriteListRequest.pageNum) &&
                Objects.equals(this.size, sellHouseFavoriteListRequest.size) &&
                Objects.equals(this.userId, sellHouseFavoriteListRequest.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNum, size, userId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SellHouseFavoriteListRequest {\n");

        sb.append("    pageNum: ").append(toIndentedString(pageNum)).append("\n");
        sb.append("    size: ").append(toIndentedString(size)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

