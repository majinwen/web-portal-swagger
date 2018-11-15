package com.toutiao.appV2.model.favorite;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * DeleteEsfFavoriteRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T08:21:25.634Z")

public class DeleteEsfFavoriteRequest {
    @JsonProperty("houseId")
    private String houseId = null;

    @JsonProperty("userId")
    private Integer userId = null;

    public DeleteEsfFavoriteRequest houseId(String houseId) {
        this.houseId = houseId;
        return this;
    }

    /**
     * Get houseId
     *
     * @return houseId
     **/
    @ApiModelProperty(value = "")


    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public DeleteEsfFavoriteRequest userId(Integer userId) {
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
        DeleteEsfFavoriteRequest deleteEsfFavoriteRequest = (DeleteEsfFavoriteRequest) o;
        return Objects.equals(this.houseId, deleteEsfFavoriteRequest.houseId) &&
                Objects.equals(this.userId, deleteEsfFavoriteRequest.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseId, userId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeleteEsfFavoriteRequest {\n");

        sb.append("    houseId: ").append(toIndentedString(houseId)).append("\n");
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

