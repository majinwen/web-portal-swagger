package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * AgentBaseDo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

/**
 * 经纪人返回实体类
 */

@Data
public class AgentBaseDo {


  /**
   * 经纪人id
   */
  @ApiModelProperty(value = "经纪人id", name = "userId")
  private String userId;
  /**
   * 经纪人名称
   */
  @ApiModelProperty(value = "经纪人名称", name = "agentName")
  private String agentName;
  /**
   * 经纪公司
   */
  @ApiModelProperty(value = "经纪公司", name = "agentCompany")
  private String agentCompany;
  /**
   * 经纪人头像
   */
  @ApiModelProperty(value = "经纪人头像", name = "headPhoto")
  private String headPhoto;
  /**
   * 经纪人虚拟电话
   */
  @ApiModelProperty(value = "经纪人虚拟电话", name = "displayPhone")
  private String displayPhone;
  /**
   * 经纪人融云信息
   */
  @ApiModelProperty(value = "经纪人融云信息", name = "rcToken")
  private String rcToken;
  /**
   * 名片
   */
  @ApiModelProperty(value = "名片", name = "agentBusinessCard")
  private String agentBusinessCard;

}
