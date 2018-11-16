package com.toutiao.appV2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserSubscribeList
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:27:32.320Z")

public class UserSubscribeList   {
    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("userSubscribeData")
    @Valid
    private List<UserSubscribe> userSubscribeData = null;

    public UserSubscribeList total(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * Get total
     * @return total
     **/
    @ApiModelProperty(value = "")


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public UserSubscribeList userSubscribeData(List<UserSubscribe> userSubscribeData) {
        this.userSubscribeData = userSubscribeData;
        return this;
    }

    public UserSubscribeList addUserSubscribeDataItem(UserSubscribe userSubscribeDataItem) {
        if (this.userSubscribeData == null) {
            this.userSubscribeData = new ArrayList<UserSubscribe>();
        }
        this.userSubscribeData.add(userSubscribeDataItem);
        return this;
    }

    /**
     * Get userSubscribeData
     * @return userSubscribeData
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<UserSubscribe> getUserSubscribeData() {
        return userSubscribeData;
    }

    public void setUserSubscribeData(List<UserSubscribe> userSubscribeData) {
        this.userSubscribeData = userSubscribeData;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserSubscribeList userSubscribeList = (UserSubscribeList) o;
        return Objects.equals(this.total, userSubscribeList.total) &&
                Objects.equals(this.userSubscribeData, userSubscribeList.userSubscribeData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, userSubscribeData);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserSubscribeList {\n");

        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    userSubscribeData: ").append(toIndentedString(userSubscribeData)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

