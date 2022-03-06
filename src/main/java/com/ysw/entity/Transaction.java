package com.ysw.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

  @Version
  private Integer version;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column
  private String transactionId;

  @Column
  private Long fromAccountId;

  @Column
  private Long toAccountId;

  @Column
  private BigDecimal amount;

  @Column
  private Date transactionDate;

}
