package com.fis.java.testfinal.service;

import com.fis.java.testfinal.entity.Account;
import com.fis.java.testfinal.entity.Customer;
import com.fis.java.testfinal.repo.AccountRepo;
import com.fis.java.testfinal.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    AccountRepo accountRepo;

    @InjectMocks
    @Autowired
    AccountServiceImpl accountService;

    Account account;

    List<Account> accounts;

    @BeforeEach
    public void setUp(){
        account = Account.builder()
                .id(4L)
                .accountNumber("1234567890123")
                .balance(8450000d)
                .createDateTime(LocalDateTime.now())
                .status(3)
                .updateDateTime(LocalDateTime.now())
                .customer(new Customer())
                .build();

        accounts = new ArrayList<>();
        accounts.add(account);
    }

    @AfterEach
    public void tearDown(){
        account = null;
        accounts = null;
    }

    @Test
    void whenFindAccountsOrderByCustomerName_thenReturnAccountList() {
        Page<Account> page = new PageImpl<>(accounts);

        when(accountRepo.findAccountsOrderByCustomerName(page.getPageable())).thenReturn(accounts);

        List<Account> list = accountService.findAccountsOrderByCustomerName(page.getPageable());

        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void givenCustomerIdAndStatus_whenFindAccountByCustomer_IdAndStatusOrderByAccountNumber_thenReturnAccountList() {
        Long id = 1L;
        Integer status = 1;
        when(accountRepo.findAccountByCustomer_IdAndStatusOrderByAccountNumber(id, status)).thenReturn(accounts);

        List<Account> list = accountService.findAccountByCustomer_IdAndStatusOrderByAccountNumber(id, status);

        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void givenAccountId_whenFindAccountById_thenReturnAccount() {
        Long id = 4L;

        when(accountRepo.findById(id)).thenReturn(Optional.of(account));

        Account acc = accountService.findAccountById(id);

        assertThat(acc.getId()).isEqualTo(4L);
    }

    @Test
    void givenAccountNumber_whenFindAccountByAccountNumber_thenReturnAccount() {
        String accountNumber = "1234567890123";

        when(accountRepo.findAccountByAccountNumber(accountNumber)).thenReturn(account);

        Account acc = accountService.findAccountByAccountNumber(accountNumber);

        assertThat(acc.getAccountNumber()).isEqualTo(accountNumber);
    }

    @Test
    void givenAccount_whenSaveAccount_thenReturnAccount() {
        when(accountRepo.save(account)).thenReturn(account);

        Account acc = accountService.saveAccount(account);

        assertThat(acc).isNotNull();
    }

    @Test
    void givenAccountId_whenApproveAccount_thenReturnAccount() {
        when(accountRepo.findById(account.getId())).thenReturn(Optional.of(account));
        when(accountRepo.save(account)).thenReturn(account);

        Account acc = accountService.approveAccount(account.getId());

        assertThat(acc.getId()).isEqualTo(account.getId());
    }

    @Test
    void givenAccountIdAndStatus_whenUpdateAccountStatus_thenReturnAccount() {
        account.setStatus(2);
        when(accountRepo.findById(account.getId())).thenReturn(Optional.of(account));
        when(accountRepo.save(account)).thenReturn(account);

        Account acc = accountService.updateAccountStatus(account.getId(), account.getStatus());

        assertThat(acc.getId()).isEqualTo(account.getId());
    }

    @Test
    void givenAccountId_whenExistById_thenReturnTrue() {
        when(accountRepo.existsById(account.getId())).thenReturn(true);

        boolean isExist = accountService.existById(account.getId());

        assertThat(isExist).isTrue();
    }
}