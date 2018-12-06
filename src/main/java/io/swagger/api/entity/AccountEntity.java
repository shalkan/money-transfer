package io.swagger.api.entity;

import java.math.BigDecimal;
import javax.persistence.*;

/**
 * @author gilfanovrenat
 */

@Entity
@Table(name = "account")
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private BigDecimal amount;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(referencedColumnName = "id", columnDefinition = "currency_id")
  private CurrencyEntity currencyEntity;

  @Column(name = "currency_id")
  private Long currencyId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public CurrencyEntity getCurrencyEntity() {
    return currencyEntity;
  }

  public void setCurrencyEntity(CurrencyEntity currencyEntity) {
    this.currencyEntity = currencyEntity;
  }

  public Long getCurrencyId() {
    return currencyId;
  }

  public void setCurrencyId(Long currencyId) {
    this.currencyId = currencyId;
  }
}
