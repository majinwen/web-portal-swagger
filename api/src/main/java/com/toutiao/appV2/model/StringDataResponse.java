package com.toutiao.appV2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * HouseComparedListDoListResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T07:53:35.212Z")

@Data
public class StringDataResponse {
    @JsonProperty("data")
    private String data = null;
}

