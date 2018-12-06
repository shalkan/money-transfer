package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Transfer
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-12-06T19:09:28.930Z[GMT]")

public class Transfer   {
  @JsonProperty("sourceAccId")
  private Long sourceAccId = null;

  @JsonProperty("destAccId")
  private Long destAccId = null;

  @JsonProperty("amount")
  private BigDecimal amount = null;

  public Transfer sourceAccId(Long sourceAccId) {
    this.sourceAccId = sourceAccId;
    return this;
  }

  /**
   * Get sourceAccId
   * @return sourceAccId
  **/
  @ApiModelProperty(value = "")


  public Long getSourceAccId() {
    return sourceAccId;
  }

  public void setSourceAccId(Long sourceAccId) {
    this.sourceAccId = sourceAccId;
  }

  public Transfer destAccId(Long destAccId) {
    this.destAccId = destAccId;
    return this;
  }

  /**
   * Get destAccId
   * @return destAccId
  **/
  @ApiModelProperty(value = "")


  public Long getDestAccId() {
    return destAccId;
  }

  public void setDestAccId(Long destAccId) {
    this.destAccId = destAccId;
  }

  public Transfer amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  **/
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transfer transfer = (Transfer) o;
    return Objects.equals(this.sourceAccId, transfer.sourceAccId) &&
        Objects.equals(this.destAccId, transfer.destAccId) &&
        Objects.equals(this.amount, transfer.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sourceAccId, destAccId, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transfer {\n");
    
    sb.append("    sourceAccId: ").append(toIndentedString(sourceAccId)).append("\n");
    sb.append("    destAccId: ").append(toIndentedString(destAccId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

