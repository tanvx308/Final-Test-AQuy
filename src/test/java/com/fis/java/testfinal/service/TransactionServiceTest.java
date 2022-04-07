package com.fis.java.testfinal.service;

import com.fis.java.testfinal.entity.Account;
import com.fis.java.testfinal.entity.Transaction;
import com.fis.java.testfinal.repo.AccountRepo;
import com.fis.java.testfinal.repo.TransactionRepo;
import com.fis.java.testfinal.service.impl.AccountServiceImpl;
import com.fis.java.testfinal.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
    @Mock
    TransactionRepo transactionRepo;

    @Mock
    AccountRepo accountRepo;

    @InjectMocks
    @Autowired
    TransactionServiceImpl transactionService;

    @Autowired
    @InjectMocks
    AccountServiceImpl accountService;

    Transaction transaction;

    List<Transaction> transactions;

    @BeforeEach
    public void setUp(){
        transaction = Transaction.builder()
                .id(9L)
                .amount(50000d)
                .content("Happy Birthday")
                .errorReason("")
                .status(0)
                .transactionDate(LocalDateTime.now())
                .fromAccount(new Account())
                .toAccount(new Account())
                .build();

        transactions = new ArrayList<>();
        transactions.add(transaction);
    }

    @AfterEach
    public void tearDown(){
        transactions = null;
        transaction = null;
    }

    @Test
    void givenTransaction_whenTransfer_thenReturnTransaction() {
        when(accountRepo.findById(transaction.getFromAccount().getId())).thenReturn(Optional.of(transaction.getFromAccount()));
        when(accountService.findAccountById(transaction.getFromAccount().getId())).thenReturn(transaction.getFromAccount());
        when(transactionRepo.save(transaction)).thenReturn(transaction);

        Transaction trans = transactionService.transfer(transaction);

        assertThat(trans).isNotNull();
    }

    @Test
    void givenTransaction_whenSaveTransaction_thenReturnTransaction() {
        when(transactionRepo.save(transaction)).thenReturn(transaction);

        Transaction trans = transactionService.saveTransaction(transaction);

        assertThat(trans.getId()).isEqualTo(transaction.getId());
    }

    @Test
    void givenAccountNumberAndTime_whenFindTransactionsByAccountNumberAndTime_thenReturnTransactionList() {
        when(accountRepo.findAccountByAccountNumber(transaction.getFromAccount().getAccountNumber())).thenReturn(transaction.getFromAccount());
        when(accountService.findAccountByAccountNumber(transaction.getFromAccount().getAccountNumber())).thenReturn(transaction.getFromAccount());
        when(transactionRepo.findTransactionsByAccountNumberAndTime(transaction.getFromAccount().getAccountNumber(),
                transaction.getTransactionDate(), transaction.getTransactionDate()));

        List<Transaction> list = transactionService.findTransactionsByAccountNumberAndTime(transaction.getFromAccount().getAccountNumber(),
                transaction.getTransactionDate().toString(), transaction.getTransactionDate().toString());
        assertThat(list).isNotNull();
    }

    @Test
    void givenTime_whenFindAllByTransactionDateBetween_thenReturnTransactionList() {
        when(transactionRepo.findAllByTransactionDateBetween(LocalDateTime.now(), LocalDateTime.now())).thenReturn(transactions);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<Transaction> list = transactionService.findAllByTransactionDateBetween(
                LocalDateTime.now().format(formatter), LocalDateTime.now().format(formatter)
        );


        assertThat(list).isNotNull();
    }
//
//    @Test
//    void findAllByTransactionDateBetweenAndStatus() {
//    }
//
//    @Test
//    void reportByDay() {
//    }
}