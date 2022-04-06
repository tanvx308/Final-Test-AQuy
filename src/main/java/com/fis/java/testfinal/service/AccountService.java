package com.fis.java.testfinal.service;

import com.fis.java.testfinal.entity.Account;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {
    List<Account> findAccountsOrderByCustomerName(Pageable pageable);

    List<Account> findAccountByCustomer_IdAndStatusOrderByAccountNumber(Long id, Integer status);

    Account findAccountById(Long id);

    Account findAccountByAccountNumber(String accountNumber);

    Account saveAccount(Account account);

    Account approveAccount(Long id);

    Account updateAccountStatus(Long id, Integer status);
    
    boolean existById(Long id);
}
