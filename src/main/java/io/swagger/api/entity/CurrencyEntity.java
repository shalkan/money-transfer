package io.swagger.api.entity;

import java.math.BigDecimal;
import javax.persistence.*;

/**
 * @author gilfanovrenat
 */

@Entity
@Table(name = "currency")
public class CurrencyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  /**
   * it is BigDecimal in case of Zimbabwe's currency use
   */
  private BigDecimal ratio;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getRatio() {
    return ratio;
  }

  public void setRatio(BigDecimal ratio) {
    this.ratio = ratio;
  }
}
