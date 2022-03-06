package com.ysw.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionInfo {

  private Long fromAccountId;
  private String toAccountNumber;
  private BigDecimal amount;

}
