package com.fis.java.testfinal.repo;

import com.fis.java.testfinal.entity.Transaction;
import com.fis.java.testfinal.model.Report;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TransactionRepoTest {
    @Autowired
    TransactionRepo transactionRepo;

    @Test
    void givenAccountNumberAndTime_whenFindTransactionsByAccountNumberAndTime_thenReturnTransactionList() {
        String accountNumber = "1234567890123";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime from = LocalDateTime.parse("2022-04-01 00:00:00", formatter);
        LocalDateTime to = LocalDateTime.parse("2022-04-06 00:00:00", formatter);
        List<Transaction> transactions = transactionRepo.findTransactionsByAccountNumberAndTime(accountNumber, from, to);

        assertThat(transactions).isNotNull();
    }

    @Test
    void givenTime_whenFindAllByTransactionDateBetween_thenReturnTransactionList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime from = LocalDateTime.parse("2022-04-01 00:00:00", formatter);
        LocalDateTime to = LocalDateTime.parse("2022-04-06 00:00:00", formatter);
        List<Transaction> transactions = transactionRepo.findAllByTransactionDateBetween(from, to);

        assertThat(transactions).isNotNull();
    }

    @Test
    void givenTimeAndStatus_whenReportByDayBetween_thenReturnReportList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime from = LocalDateTime.parse("2022-04-01 00:00:00", formatter);
        LocalDateTime to = LocalDateTime.parse("2022-04-06 00:00:00", formatter);
        Integer status = 1;
        List<Report> reports = transactionRepo.reportByDayBetween(from, to, status);

        assertThat(reports).isNotNull();
    }

    @Test
    void giveDay_whenReportByDay_thenReturnObjectArrayList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now().minus(Period.ofDays(1));
        LocalDateTime startOfDay = localDate.atTime(LocalTime.MIN);
        LocalDateTime endOfDay = localDate.atTime(LocalTime.MAX);
        List<Object[]> objects = transactionRepo.reportByDay(startOfDay, endOfDay);

        assertThat(objects).isNotNull();
    }
}