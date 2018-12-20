package com.toutiao.appV2.model.HomePage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.toutiao.app.domain.plot.PlotDetailsFewDo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PlotDetailsFewDoList
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T09:20:13.068Z")

public class PlotDetailsFewDoList   {
    @JsonProperty("plotDetailsFewDos")
    @Valid
    private List<PlotDetailsFewDo> plotDetailsFewDos = null;

    @JsonProperty("total")
    private Integer total = null;

    public PlotDetailsFewDoList plotDetailsFewDos(List<PlotDetailsFewDo> plotDetailsFewDos) {
        this.plotDetailsFewDos = plotDetailsFewDos;
        return this;
    }

    public PlotDetailsFewDoList addPlotDetailsFewDosItem(PlotDetailsFewDo plotDetailsFewDosItem) {
        if (this.plotDetailsFewDos == null) {
            this.plotDetailsFewDos = new ArrayList<PlotDetailsFewDo>();
        }
        this.plotDetailsFewDos.add(plotDetailsFewDosItem);
        return this;
    }

    /**
     * Get plotDetailsFewDos
     * @return plotDetailsFewDos
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<PlotDetailsFewDo> getPlotDetailsFewDos() {
        return plotDetailsFewDos;
    }

    public void setPlotDetailsFewDos(List<PlotDetailsFewDo> plotDetailsFewDos) {
        this.plotDetailsFewDos = plotDetailsFewDos;
    }

    public PlotDetailsFewDoList total(Integer total) {
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


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlotDetailsFewDoList plotDetailsFewDoList = (PlotDetailsFewDoList) o;
        return Objects.equals(this.plotDetailsFewDos, plotDetailsFewDoList.plotDetailsFewDos) &&
                Objects.equals(this.total, plotDetailsFewDoList.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plotDetailsFewDos, total);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PlotDetailsFewDoList {\n");

        sb.append("    plotDetailsFewDos: ").append(toIndentedString(plotDetailsFewDos)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
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

