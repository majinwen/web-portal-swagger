package com.toutiao.appV2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.subscribe.UserSubscribeListDo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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

@Data
public class UserSubscribeListDoList   {
    @JsonProperty("totalNum")
    private Integer totalNum = null;

    @JsonProperty("userSubscribeListDoData")
    @Valid
    private List<UserSubscribeListDo> userSubscribeListDoData = null;
}

