package com.ysw.dao;

import com.ysw.entity.Account;
import com.ysw.entity.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  List<Account> findByAccountTypeNotIn(List<AccountType> accountTypes);
  List<Account> findByAccountTypeIn(List<AccountType> accountTypes);
  Optional<Account> findByAccountNumber(String accountNumber);
}
