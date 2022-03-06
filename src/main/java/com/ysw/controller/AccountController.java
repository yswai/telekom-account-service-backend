package com.ysw.controller;

import com.google.common.collect.ImmutableMap;
import com.ysw.entity.Account;
import com.ysw.model.AccountCreation;
import com.ysw.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("")
@Slf4j
public class AccountController {

  @Autowired
  private AccountService accountService;

  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity listAccounts() {
    List<Account> accounts = accountService.listAccounts();
    return ResponseEntity.ok(accounts);
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity createAccount(@RequestBody AccountCreation accountCreation) {
    Account account = accountService.createInvestmentAccount(accountCreation.getAccountName(), accountCreation.getAccountType());
    return ResponseEntity.ok(account);
  }

  @RequestMapping(value = "/investments/{accountId}", method = RequestMethod.DELETE, produces = "application/json")
  public ResponseEntity deleteInvestmentAccounts(@PathVariable("accountId") Long accountId) {
    accountService.closeAccount(accountId);
    return ResponseEntity.ok(ImmutableMap.of("status", "ok"));
  }

}
