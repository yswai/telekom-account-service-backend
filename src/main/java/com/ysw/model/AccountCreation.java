package com.ysw.model;

import com.ysw.entity.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreation {

  private String accountName;
  private AccountType accountType;

}
