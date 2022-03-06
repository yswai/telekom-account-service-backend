package com.ysw.service;

import com.ysw.dao.AccountRepository;
import com.ysw.entity.Account;
import com.ysw.entity.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private TransactionService transactionService;

  public List<Account> listAccounts() {
    return accountRepository.findAll();
  }

  @Transactional
  public Account createInvestmentAccount(String accountName, AccountType accountType) {
    Account newInvestmentAccount = new Account();
    newInvestmentAccount.setName(accountName);
    newInvestmentAccount.setAccountNumber(UUID.randomUUID().toString().replace("-", ""));
    newInvestmentAccount.setAccountType(accountType);
    newInvestmentAccount.setBalance(BigDecimal.ZERO);
    Account parentAccount = getParentAccount();
    parentAccount.getSubAccounts().add(newInvestmentAccount);
    newInvestmentAccount.setParentAccount(parentAccount);
    accountRepository.saveAll(asList(newInvestmentAccount, parentAccount));
    return newInvestmentAccount;
  }

  @Transactional
  public void closeAccount(Long accountId) {
    Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account does not exists"));
    Account parentAccount = account.getParentAccount();
    transactionService.performTransaction(account.getId(), parentAccount.getAccountNumber(), account.getBalance());
    accountRepository.delete(account);
  }

  public Account getParentAccount() {
    return accountRepository.findByAccountTypeIn(asList(AccountType.SAVINGS)).stream().findFirst()
        .orElseThrow(() -> new RuntimeException("Invalid parent account"));
  }
}
