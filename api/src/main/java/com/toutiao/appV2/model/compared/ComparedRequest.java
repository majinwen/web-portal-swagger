package com.toutiao.appV2.model.compared;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ComparedRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T07:53:35.212Z")

public class ComparedRequest {
    @JsonProperty("houseId")
    @ApiModelProperty(value = "房源id")
    private String houseId = null;

    @ApiModelProperty(value = "id")
    @JsonProperty("id")
    private Integer id = null;

    public ComparedRequest houseId(String houseId) {
        this.houseId = houseId;
        return this;
    }

    /**
     * Get houseId
     *
     * @return houseId
     **/


    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public ComparedRequest id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ComparedRequest comparedRequest = (ComparedRequest) o;
        return Objects.equals(this.houseId, comparedRequest.houseId) &&
                Objects.equals(this.id, comparedRequest.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseId, id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ComparedRequest {\n");

        sb.append("    houseId: ").append(toIndentedString(houseId)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

