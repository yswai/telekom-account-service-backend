package com.ysw.service;

import com.ysw.dao.AccountRepository;
import com.ysw.dao.TransactionRepository;
import com.ysw.entity.Account;
import com.ysw.entity.Transaction;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private TransactionRepository transactionRepository;

  @Transactional
  public Transaction performTransaction(Long fromAccountId, String toAccountNumber, BigDecimal amount) {
    Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow(() -> new IllegalArgumentException("Invalid from account id: " + fromAccountId));
    if (fromAccount.getBalance().compareTo(amount) < 0) {
      throw new RuntimeException("Insufficient balance");
    }
    Account toAccount = accountRepository.findByAccountNumber(toAccountNumber).orElseThrow(() -> new IllegalArgumentException("Invalid to account number: " + toAccountNumber));
    if (fromAccount.getId().equals(toAccount.getId())) {
      throw new IllegalArgumentException("Transfer to same account is not allowed");
    }
    fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
    toAccount.setBalance(toAccount.getBalance().add(amount));
    Transaction transaction = new Transaction();
    transaction.setFromAccountId(fromAccountId);
    transaction.setToAccountId(toAccount.getId());
    transaction.setTransactionId(UUID.randomUUID().toString());
    transaction.setAmount(amount);
    transaction.setTransactionDate(new Date(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000));
    try {
      transaction = transactionRepository.save(transaction);
      accountRepository.save(fromAccount);
      accountRepository.save(toAccount);
    } catch (OptimisticEntityLockException ex) {
      throw new RuntimeException("Transaction rolled back due to multiple concurrent transaction occurred");
    }
    return transaction;
  }

  public List<Transaction> listTransactionsForAccount(Long accountId) {
    return transactionRepository.findByFromAccountId(accountId);
  }

}
