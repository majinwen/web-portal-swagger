package com.toutiao.appV2.model.compared;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.compared.HouseComparedListDo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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

@Data
public class HouseComparedIdResponse {
    @JsonProperty("houseIds")
    private String houseIds = null;
}

