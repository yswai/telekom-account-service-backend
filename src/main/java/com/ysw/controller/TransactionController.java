package com.ysw.controller;

import com.ysw.entity.Transaction;
import com.ysw.model.TransactionInfo;
import com.ysw.service.TransactionService;
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
@RequestMapping("/transactions")
@Slf4j
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity createTransaction(@RequestBody TransactionInfo transactionInfo) {
    Transaction transaction = transactionService.performTransaction(
        transactionInfo.getFromAccountId(), transactionInfo.getToAccountNumber(), transactionInfo.getAmount()
    );
    return ResponseEntity.ok(transaction);
  }

  @RequestMapping(value = "/{accountId}", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity listAccountTransactions(@PathVariable("accountId") Long accountId) {
    List<Transaction> transactions = transactionService.listTransactionsForAccount(accountId);
    return ResponseEntity.ok(transactions);
  }
}
