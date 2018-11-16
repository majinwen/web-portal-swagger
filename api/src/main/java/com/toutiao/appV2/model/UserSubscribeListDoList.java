package com.toutiao.appV2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.subscribe.UserSubscribeListDo;
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
 * UserSubscribeListDoList
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:54:57.091Z")

public class UserSubscribeListDoList   {
    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("userSubscribeListDoData")
    @Valid
    private List<UserSubscribeListDo> userSubscribeListDoData = null;

    public UserSubscribeListDoList total(Integer total) {
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

    public UserSubscribeListDoList userSubscribeListDoData(List<UserSubscribeListDo> userSubscribeListDoData) {
        this.userSubscribeListDoData = userSubscribeListDoData;
        return this;
    }

    public UserSubscribeListDoList addUserSubscribeListDoDataItem(UserSubscribeListDo userSubscribeListDoDataItem) {
        if (this.userSubscribeListDoData == null) {
            this.userSubscribeListDoData = new ArrayList<UserSubscribeListDo>();
        }
        this.userSubscribeListDoData.add(userSubscribeListDoDataItem);
        return this;
    }

    /**
     * Get userSubscribeListDoData
     * @return userSubscribeListDoData
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<UserSubscribeListDo> getUserSubscribeListDoData() {
        return userSubscribeListDoData;
    }

    public void setUserSubscribeListDoData(List<UserSubscribeListDo> userSubscribeListDoData) {
        this.userSubscribeListDoData = userSubscribeListDoData;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserSubscribeListDoList userSubscribeListDoList = (UserSubscribeListDoList) o;
        return Objects.equals(this.total, userSubscribeListDoList.total) &&
                Objects.equals(this.userSubscribeListDoData, userSubscribeListDoList.userSubscribeListDoData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, userSubscribeListDoData);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserSubscribeListDoList {\n");

        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    userSubscribeListDoData: ").append(toIndentedString(userSubscribeListDoData)).append("\n");
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

