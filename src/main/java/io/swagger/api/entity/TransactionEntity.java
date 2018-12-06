package io.swagger.api.entity;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.model.Transaction;
import org.threeten.bp.OffsetDateTime;

/**
 * @author gilfanovrenat
 */

@Entity
@Table(name = "transaction")
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "source_account_id")
  private Long sourceAccId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(columnDefinition = "source_account_id", referencedColumnName = "id")
  private AccountEntity sourceAcc;

  @Column(name = "destination_account_id")
  private Long destAccId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(columnDefinition = "destination_account_id", referencedColumnName = "id")
  private AccountEntity destAcc;

  @Enumerated(EnumType.STRING)
  private Transaction.StatusEnum status;

  private BigDecimal amount;

  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(name = "start_date")
  private Date startDate;

  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(name = "end_date")
  private Date endDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "operation_type")
  private Transaction.OperationTypeEnum operationType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getSourceAccId() {
    return sourceAccId;
  }

  public void setSourceAccId(Long sourceAccId) {
    this.sourceAccId = sourceAccId;
  }

  public AccountEntity getSourceAcc() {
    return sourceAcc;
  }

  public void setSourceAcc(AccountEntity sourceAcc) {
    this.sourceAcc = sourceAcc;
  }

  public Long getDestAccId() {
    return destAccId;
  }

  public void setDestAccId(Long destAccId) {
    this.destAccId = destAccId;
  }

  public AccountEntity getDestAcc() {
    return destAcc;
  }

  public void setDestAcc(AccountEntity destAcc) {
    this.destAcc = destAcc;
  }

  public Transaction.StatusEnum getStatus() {
    return status;
  }

  public void setStatus(Transaction.StatusEnum status) {
    this.status = status;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Transaction.OperationTypeEnum getOperationType() {
    return operationType;
  }

  public void setOperationType(Transaction.OperationTypeEnum operationType) {
    this.operationType = operationType;
  }
}
