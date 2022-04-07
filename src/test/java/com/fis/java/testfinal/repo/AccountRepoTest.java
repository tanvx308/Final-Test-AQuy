package com.fis.java.testfinal.repo;

import com.fis.java.testfinal.entity.Account;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepoTest {
    @Autowired
    AccountRepo accountRepo;

    @Test
    void whenFindAccountsOrderByCustomerName_thenReturnAccountList() {
        Pageable pageable = PageRequest.of(0, 6);
        List<Account> accounts = accountRepo.findAccountsOrderByCustomerName(pageable);

        assertThat(accounts).isNotNull();
    }

    @Test
    void givenCustomerId_whenFindAccountByCustomerId_thenReturnAccountList() {
        Long id = 1L;
        List<Account> accounts = accountRepo.findAccountByCustomer_Id(id);

        assertThat(accounts).isNotNull();
    }

    @Test
    void givenAccountNumber_whenFindAccountByAccountNumber_thenReturnAccount() {
        String accountNumber = "1234567890123";
        Account account = accountRepo.findAccountByAccountNumber(accountNumber);

        assertThat(account).isNotNull();
    }

    @Test
    void givenCustomerIdAndStatus_whenFindAccountByCustomerIdAndStatusOrderByAccountNumber_thenReturnAccountList() {
        Long id = 1L;
        Integer status = 1;
        List<Account> accounts = accountRepo.findAccountByCustomer_IdAndStatusOrderByAccountNumber(id ,status);

        assertThat(accounts).isNotNull();
    }
}