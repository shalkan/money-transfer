package io.swagger.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Transaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-12-06T19:09:28.930Z[GMT]")

public class Transaction   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("sourceAccId")
  private String sourceAccId = null;

  @JsonProperty("destAccId")
  private String destAccId = null;

  @JsonIgnore
  private Account sourceAcc;

  @JsonIgnore
  private Account destAccount;

  public Account getSourceAcc() {
    return sourceAcc;
  }

  public void setSourceAcc(Account sourceAcc) {
    this.sourceAcc = sourceAcc;
  }

  public Account getDestAccount() {
    return destAccount;
  }

  public void setDestAccount(Account destAccount) {
    this.destAccount = destAccount;
  }

  /**
   * Transaction Status
   */
  public enum StatusEnum {
    ACTIVE("active"),
    
    COMPLETE("complete"),
    
    FAILED("failed");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("amount")
  private BigDecimal amount = null;

  @JsonProperty("startDate")
  private OffsetDateTime startDate = null;

  @JsonProperty("endDate")
  private OffsetDateTime endDate = null;

  /**
   * operation type
   */
  public enum OperationTypeEnum {
    INITIALINCOME("initialIncome"),
    
    TRANSFER("transfer");

    private String value;

    OperationTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static OperationTypeEnum fromValue(String text) {
      for (OperationTypeEnum b : OperationTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("operationType")
  private OperationTypeEnum operationType = null;

  public Transaction id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Transaction sourceAccId(String sourceAccId) {
    this.sourceAccId = sourceAccId;
    return this;
  }

  /**
   * Get sourceAccId
   * @return sourceAccId
  **/
  @ApiModelProperty(value = "")


  public String getSourceAccId() {
    return sourceAccId;
  }

  public void setSourceAccId(String sourceAccId) {
    this.sourceAccId = sourceAccId;
  }

  public Transaction destAccId(String destAccId) {
    this.destAccId = destAccId;
    return this;
  }

  /**
   * Get destAccId
   * @return destAccId
  **/
  @ApiModelProperty(value = "")


  public String getDestAccId() {
    return destAccId;
  }

  public void setDestAccId(String destAccId) {
    this.destAccId = destAccId;
  }

  public Transaction status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Transaction Status
   * @return status
  **/
  @ApiModelProperty(value = "Transaction Status")


  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Transaction amount(BigDecimal amount) {
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

  public Transaction startDate(OffsetDateTime startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Get startDate
   * @return startDate
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(OffsetDateTime startDate) {
    this.startDate = startDate;
  }

  public Transaction endDate(OffsetDateTime endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * Get endDate
   * @return endDate
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(OffsetDateTime endDate) {
    this.endDate = endDate;
  }

  public Transaction operationType(OperationTypeEnum operationType) {
    this.operationType = operationType;
    return this;
  }

  /**
   * operation type
   * @return operationType
  **/
  @ApiModelProperty(value = "operation type")


  public OperationTypeEnum getOperationType() {
    return operationType;
  }

  public void setOperationType(OperationTypeEnum operationType) {
    this.operationType = operationType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction transaction = (Transaction) o;
    return Objects.equals(this.id, transaction.id) &&
        Objects.equals(this.sourceAccId, transaction.sourceAccId) &&
        Objects.equals(this.destAccId, transaction.destAccId) &&
        Objects.equals(this.status, transaction.status) &&
        Objects.equals(this.amount, transaction.amount) &&
        Objects.equals(this.startDate, transaction.startDate) &&
        Objects.equals(this.endDate, transaction.endDate) &&
        Objects.equals(this.operationType, transaction.operationType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, sourceAccId, destAccId, amount, startDate, operationType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    sourceAccId: ").append(toIndentedString(sourceAccId)).append("\n");
    sb.append("    destAccId: ").append(toIndentedString(destAccId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    operationType: ").append(toIndentedString(operationType)).append("\n");
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

