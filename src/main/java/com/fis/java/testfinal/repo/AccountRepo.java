package com.fis.java.testfinal.repo;

import com.fis.java.testfinal.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a ORDER BY a.customer.name")
    List<Account> findAccountsOrderByCustomerName(Pageable pageable);

    List<Account> findAccountByCustomer_Id(Long id);

    Account findAccountByAccountNumber(String accountNumber);

    List<Account> findAccountByCustomer_IdAndStatusOrderByAccountNumber(Long id, Integer status);
}
