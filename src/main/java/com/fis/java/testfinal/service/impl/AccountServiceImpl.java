package com.fis.java.testfinal.service.impl;

import com.fis.java.testfinal.constant.AccountStatus;
import com.fis.java.testfinal.entity.Account;
import com.fis.java.testfinal.entity.Customer;
import com.fis.java.testfinal.exception.AppException;
import com.fis.java.testfinal.model.ErrorMessage;
import com.fis.java.testfinal.repo.AccountRepo;
import com.fis.java.testfinal.service.AccountService;
import com.fis.java.testfinal.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepo accountRepo;

    @Autowired
    CustomerService customerService;

    @Override
    public List<Account> findAccountsOrderByCustomerName(Pageable pageable) {
        return accountRepo.findAccountsOrderByCustomerName(pageable);
    }

    @Override
    public List<Account> findAccountByCustomer_IdAndStatusOrderByAccountNumber(Long id, Integer status) {
        Customer customer = customerService.findCustomerById(id);
        if(customer == null){
            throw new AppException("Customer", new ErrorMessage("CUS404", "Customer is not exist."));
        }
        if(status == null){
            List<Account> accounts = accountRepo.findAccountByCustomer_Id(id);
            Collections.sort(accounts, new Comparator<Account>() {
                @Override
                public int compare(Account o1, Account o2) {
                    if(o1.getStatus().equals(AccountStatus.ACTIVE) && !o2.getStatus().equals(AccountStatus.ACTIVE)){
                        return 1;
                    }else if(!o1.getStatus().equals(AccountStatus.ACTIVE) && o2.getStatus().equals(AccountStatus.ACTIVE)){
                        return -1;
                    } else {
                       return o1.getAccountNumber().compareTo(o2.getAccountNumber());
                    }
                }
            });
            return accounts;
        }
        return accountRepo.findAccountByCustomer_IdAndStatusOrderByAccountNumber(id, status);
    }

    @Override
    public Account findAccountById(Long id) {
        Optional<Account> account = accountRepo.findById(id);
        if(account.isPresent()){
            return account.get();
        }else{
            throw new AppException("Account", new ErrorMessage("ACC404", "Account is not exist."));
        }
    }

    @Override
    public Account findAccountByAccountNumber(String accountNumber) {
        Account account = accountRepo.findAccountByAccountNumber(accountNumber);
        if(account == null){
            throw new AppException("Account", new ErrorMessage("CUS404", "Account is not exist."));
        }
        return account;
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public Account approveAccount(Long id) {
        Optional<Account> optionalAccount = accountRepo.findById(id);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            if(account.getStatus().equals(AccountStatus.APPROVE)){
                account.setStatus(AccountStatus.ACTIVE);
                return accountRepo.save(account);
            }else{
                throw new AppException("Account", new ErrorMessage("CUS401", "Account`s Status is not valid."));
            }
        }else{
            throw new AppException("Account", new ErrorMessage("CUS404", "Account is not exist."));
        }
    }

    @Override
    public Account updateAccountStatus(Long id, Integer status) {
        if(!status.equals(AccountStatus.IN_ACTIVE) && !status.equals(AccountStatus.LOCK)){
            throw new AppException("Account", new ErrorMessage("CUS400", "Account`s Status is not valid."));
        }
        Optional<Account> optionalAccount = accountRepo.findById(id);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            account.setStatus(status);
            return accountRepo.save(account);
        }else{
            throw new AppException("Account", new ErrorMessage("CUS404", "Account is not exist."));
        }
    }

    @Override
    public boolean existById(Long id) {
        return accountRepo.existsById(id);
    }
}
