package com.fis.java.testfinal.repo;

import com.fis.java.testfinal.entity.Transaction;
import com.fis.java.testfinal.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    @Query(name = "findTransactionsByAccountNumberAndTime")
    List<Transaction> findTransactionsByAccountNumberAndTime(@Param("number") String accountNumber, @Param("fromDate") LocalDateTime from, @Param("toDate") LocalDateTime to);

    List<Transaction> findAllByTransactionDateBetween(LocalDateTime from, LocalDateTime to);

    @Query(name = "reportByDayBetween")
    List<Report> reportByDayBetween(@Param("fromDate")LocalDateTime from, @Param("toDate") LocalDateTime to, @Param("status") Integer status);

    @Query(name = "reportByDay")
    List<Object[]> reportByDay(LocalDateTime dateTime);
}
